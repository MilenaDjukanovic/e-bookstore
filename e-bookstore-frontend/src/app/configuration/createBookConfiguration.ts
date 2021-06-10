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
    type: 'upload-imgur',
    label: 'Image URL',
    inputType: 'text',
    showButton: true,
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
    name: 'inStock',
    validations: []
  },
  {
    type: 'rest-select',
    name: 'authorId',
    label: 'Author',
    optionsUrl: AuthorsApi.public.findAllNoLimit,
    optionsArrayProperty: 'content',
    optionsDisplayProperty: 'firstName',
    optionsValueProperty: 'id',
    entityCreateComponentDialog: AuthorDialogComponent
  },
  {
    type: 'rest-select',
    label: 'Category',
    name: 'categoryId',
    optionsUrl: CategoryApi.public.findAll,
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
