import { Validators } from "@angular/forms";
import { AuthorDialogComponent } from "../shared/components/author-dialog/author-dialog.component";
import { CategoryDialogComponent } from "../shared/components/category-dialog/category-dialog.component";
import {FieldConfig} from "../shared/model/form/field.interface";
import { AuthorsApi } from "./api/authors-api";
import { CategoryApi } from "./api/category-api";

export const defaultBookConfiguration: FieldConfig[] = [
  {
    type: 'input',
    label: 'Title',
    inputType: 'text',
    name: 'title',
    validations: [Validators.required],
    error: 'Title must not be empty!'
  },
  {
    type: 'input',
    label: 'Description',
    inputType: 'text',
    name: 'description',
    validations: [Validators.required],
    error: 'Description must not be empty!'
  },
  {
    type: 'upload-imgur',
    label: 'Image URL',
    inputType: 'url',
    showButton: true,
    name: 'image',
    validations: [Validators.required, Validators.pattern('(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?')],
    error: 'Please provide the url to the cover image or upload a new one!'
  },
  {
    type: 'input',
    label: 'Price',
    inputType: 'number',
    name: 'price',
    validations: [Validators.required, Validators.min(1)],
    error: 'Price cannot be empty or less than 1!'
  },
  {
    type: 'input',
    label: 'In Stock',
    inputType: 'number',
    name: 'inStock',
    validations: [Validators.required, Validators.min(0)],
    error: 'In Stock cannot be empty or less than 0!'
  },
  {
    type: 'rest-select',
    name: 'authorId',
    label: 'Author',
    showButton: true,
    optionsUrl: AuthorsApi.public.findAllNoLimit,
    optionsArrayProperty: 'content',
    optionsDisplayProperty: 'firstName',
    optionsDisplayComplexProperty: {
      properties: ['firstName', 'birthYear', 'lastName'],
      format: '{firstName} {lastName} ({birthYear})'
    },
    optionsValueProperty: 'id',
    entityCreateComponentDialog: AuthorDialogComponent,
    validations: [Validators.required],
    error: 'Author cannot be empty!'
  },
  {
    type: 'rest-select',
    label: 'Category',
    name: 'categoryId',
    optionsUrl: CategoryApi.public.findAllNoLimit,
    optionsArrayProperty: 'content',
    optionsDisplayProperty: 'name',
    optionsValueProperty: 'id',
    showButton: true,
    entityCreateComponentDialog: CategoryDialogComponent,
    validations: [Validators.required],
    error: 'Category cannot be empty!'
  },
  {
    type: 'button',
    name: 'saveBook',
    label: 'Save'
  }
]
