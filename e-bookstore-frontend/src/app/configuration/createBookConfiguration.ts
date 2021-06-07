import { AuthorDialogComponent } from "../shared/components/author-dialog/author-dialog.component";
import { CategoryDialogComponent } from "../shared/components/category-dialog/category-dialog.component";
import {FieldConfig} from "../shared/model/form/field.interface";

export const defaultBookConfiguration: FieldConfig[] = [
  {
    type: 'input',
    label: 'Title',
    inputType: 'text',
    name: 'title',
    validations: []
  },
  {
    type: 'input',
    label: 'Description',
    inputType: 'text',
    name: 'description',
    validations: []
  },
  {
    type: 'input',
    label: 'ImageURL',
    inputType: 'text',
    name: 'image',
    validations: []
  },
  {
    type: 'input',
    label: 'Price',
    inputType: 'text',
    name: 'price',
    validations: []
  },
  {
    type: 'input',
    label: 'In storage',
    inputType: 'text',
    name: 'inStorage',
    validations: []
  },
  {
    type: 'rest-select',
    name: 'author',
    label: 'Author',
    optionsUrl: '/spring/api/public/authors/all',
    optionsArrayProperty: 'content',
    optionsDisplayProperty: 'firstName',
    optionsValueProperty: 'id',
    entityCreateComponentDialog: AuthorDialogComponent
  },
  {
    type: 'rest-select',
    label: 'Category',
    name: 'category',
    optionsUrl: '/spring/api/public/categories/all',
    optionsArrayProperty: 'content',
    optionsDisplayProperty: 'name',
    optionsValueProperty: 'id',
    showButton: true,
    entityCreateComponentDialog: CategoryDialogComponent
  },
  {
    type: 'button',
    name: 'saveBook',
    label: 'Save'
  }
]
