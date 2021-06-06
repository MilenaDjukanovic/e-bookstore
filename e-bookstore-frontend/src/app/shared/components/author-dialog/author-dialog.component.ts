import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {DialogData, DynamicFormComponent} from "../form/dynamic-form/dynamic-form.component";
import {FieldConfig} from "../../model/form/field.interface";
import {authorDialogConfiguration} from "../../../configuration/authorDialogConfiguration";

@Component({
  selector: 'app-author-dialog',
  templateUrl: './author-dialog.component.html',
  styleUrls: ['./author-dialog.component.css']
})
export class AuthorDialogComponent implements OnInit {

  @ViewChild(DynamicFormComponent) form!: DynamicFormComponent;
  authorDialogFields: FieldConfig[] = authorDialogConfiguration;

  constructor(public dialogRef: MatDialogRef<AuthorDialogComponent>) { }

  ngOnInit(): void {
  }

  public submit(value: any) {

  }
}
