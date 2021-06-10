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
  private successfullyCreated = false;

  constructor(public dialogRef: MatDialogRef<AuthorDialogComponent>,
              private authorService: AuthorService) { }

  ngOnInit(): void {
  }

  public submit(value: any) {
      const createAuthor: ICreateAuthor = new CreateAuthor(
        value.firstname, value.lastname, value.about
      );

      this.authorService.createAuthor(createAuthor)
        .subscribe(() => {
          this.successfullyCreated = true;
        }, (error => {
          this.successfullyCreated = false;
        }))

      this.dialogRef.close({successful: this.successfullyCreated});
  }
}
