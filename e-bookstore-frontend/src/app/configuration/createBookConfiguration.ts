import {FieldConfig} from "../shared/model/form/field.interface";

export const createExistingBookManagementRequestConfiguration: FieldConfig[] = [
  {
    type: 'input',
    label: 'Title',
    inputType: 'text',
    name: 'title',
    validations: []
  },
  {
    type: 'input',
    label: 'Quantity',
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
    showButton: true,
    actionName: 'createAuthor'

  },
  {
    type: 'select',
    label: 'Book',
    name: 'book',
    value: 'book',
    options: ['neko', 'nesto'],
  },
  {
    type: 'input',
    label: 'Reason',
    inputType: 'text',
    name: 'reason',
    validations: []
  }, {
    type: 'button',
    name: 'save',
    label: 'save'
  }
]
