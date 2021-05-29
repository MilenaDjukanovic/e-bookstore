import {Component, OnInit, ViewChild} from '@angular/core';
import {DynamicFormComponent} from "../../shared/components/form/dynamic-form/dynamic-form.component";
import {FieldConfig} from "../../shared/model/form/field.interface";
import {createBookConfiguration} from "../../configuration/createBookConfiguration";

@Component({
  selector: 'app-create-book-request',
  templateUrl: './create-book-request.component.html',
  styleUrls: ['./create-book-request.component.css']
})
export class CreateBookRequestComponent implements OnInit {

  @ViewChild(DynamicFormComponent) form!: DynamicFormComponent;
  createBook: FieldConfig[] = createBookConfiguration;

  submit(value: any) {
    console.log(value);
  }
  constructor() { }

  ngOnInit(): void {
  }

}
