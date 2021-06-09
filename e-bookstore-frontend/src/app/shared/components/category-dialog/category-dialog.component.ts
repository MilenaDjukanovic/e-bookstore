import {Component, OnInit, ViewChild} from '@angular/core';
import {DynamicFormComponent} from "../form/dynamic-form/dynamic-form.component";
import {FieldConfig} from "../../model/form/field.interface";
import {categoryDialogConfiguration} from "../../../configuration/categoryDialogConfiguration";
import {MatDialogRef} from "@angular/material/dialog";
import {CreateCategory, ICreateCategory} from "../../model/category.model";
import {CategoryService} from "../../../services/core/category.service";

@Component({
  selector: 'app-category-dialog',
  templateUrl: './category-dialog.component.html',
  styleUrls: ['./category-dialog.component.css']
})
export class CategoryDialogComponent implements OnInit {

  @ViewChild(DynamicFormComponent) form!:DynamicFormComponent;
  public categoryDialogFields: FieldConfig[] = categoryDialogConfiguration;
  private successfullyCreated = false;

  constructor(public dialogRef: MatDialogRef<CategoryDialogComponent>,
              private categoryService: CategoryService) { }

  ngOnInit(): void {
  }

  public submit(value: any) {
    const category: ICreateCategory = new CreateCategory(value.name);
    this.categoryService.createCategory(category)
      .subscribe(() => {
        this.successfullyCreated = true;
      }, (error) => {
        this.successfullyCreated = false;
      })

    this.dialogRef.close({successful: this.successfullyCreated})
  }

}
