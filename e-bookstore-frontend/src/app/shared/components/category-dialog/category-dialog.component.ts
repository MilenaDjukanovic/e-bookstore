import {Component, OnInit, ViewChild} from '@angular/core';
import {DynamicFormComponent} from "../form/dynamic-form/dynamic-form.component";
import {FieldConfig} from "../../model/form/field.interface";
import {categoryDialogConfiguration} from "../../../configuration/categoryDialogConfiguration";
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-category-dialog',
  templateUrl: './category-dialog.component.html',
  styleUrls: ['./category-dialog.component.css']
})
export class CategoryDialogComponent implements OnInit {

  @ViewChild(DynamicFormComponent) form!:DynamicFormComponent;
  categoryDialogFields: FieldConfig[] = categoryDialogConfiguration;

  constructor(public dialogRef: MatDialogRef<CategoryDialogComponent>) { }

  ngOnInit(): void {
  }

  public submit(value: any) {
    console.log();
  }

}
