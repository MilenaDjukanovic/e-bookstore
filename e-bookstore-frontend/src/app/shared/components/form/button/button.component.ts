import { Component, OnInit } from '@angular/core';
import {FieldConfig} from "../../../model/form/field.interface";
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.css']
})
export class ButtonComponent implements OnInit {

  public field!: FieldConfig;
  public group!: FormGroup;

  constructor() { }

  ngOnInit(): void {
  }

}
