import { Component, OnInit } from '@angular/core';
import {FieldConfig} from "../../../model/form/field.interface";
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.css']
})
export class InputComponent implements OnInit {

  public field!: FieldConfig;
  public group!:FormGroup;

  constructor() { }

  ngOnInit(): void {
  }

}
