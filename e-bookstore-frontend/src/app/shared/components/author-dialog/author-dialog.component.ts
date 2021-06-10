import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {DialogData, DynamicFormComponent} from "../form/dynamic-form/dynamic-form.component";
import {FieldConfig} from "../../model/form/field.interface";
import {authorDialogConfiguration} from "../../../configuration/authorDialogConfiguration";
import {AuthorService} from "../../../services/core/author.service";
import {CreateAuthor, ICreateAuthor} from "../../model/author.model";

@Component({
  selector: 'app-author-dialog',
  templateUrl: './author-dialog.component.html',
  styleUrls: ['./author-dialog.component.css']
})
export class AuthorDialogComponent implements OnInit {

  @ViewChild(DynamicFormComponent) form!: DynamicFormComponent;

  public authorDialogFields: FieldConfig[] = authorDialogConfiguration;
  public error!: string;

  constructor(public dialogRef: MatDialogRef<AuthorDialogComponent>,
              private authorService: AuthorService) { }

  ngOnInit(): void {
  }

  public submit(createAuthor: CreateAuthor) {
    this.authorService.createAuthor(createAuthor).subscribe(() => {
      this.error = '';
      this.dialogRef.close({successful: true});
    }, error => {
      this.error = 'ERROR: ' + error;
    });
  }
}
