import {FieldConfig} from "../shared/model/form/field.interface";

export const createBookConfiguration: FieldConfig[] = [
  {
    type: 'input',
    label: 'Title',
    inputType: 'text',
    name: 'title',
    validations: []
  },
  {
    type: 'select',
    label: 'Author',
    name: 'author',
    value: 'author',
    options: ['neko', 'nesto'],
  },
  {
    type: 'input',
    label: 'Description',
    inputType: 'text',
    name: 'description',
    validations: []
  }, {
    type: 'button',
    name: 'save',
    label: 'save'
  }
]
