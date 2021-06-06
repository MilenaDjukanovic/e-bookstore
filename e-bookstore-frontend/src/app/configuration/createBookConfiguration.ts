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
    type: 'select',
    label: 'Author',
    name: 'author',
    value: 'author',
    options: ['neko', 'nesto'],
    showButton: true,
    actionName: 'createAuthor'

  },
  {
    type: 'select',
    label: 'Category',
    name: 'category',
    value: 'category',
    options: ['neko', 'nesto'],
    showButton: true,
    actionName: 'createCategory'
  },
  {
    type: 'button',
    name: 'saveBook',
    label: 'Save'
  }
]
