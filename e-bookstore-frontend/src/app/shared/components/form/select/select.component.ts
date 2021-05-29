import { Component, OnInit } from '@angular/core';
import {FieldConfig} from "../../../model/form/field.interface";
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.css']
})
export class SelectComponent implements OnInit {

  public field!: FieldConfig;
  public group!: FormGroup;

  constructor() { }

  ngOnInit(): void {
  }

}
