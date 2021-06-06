import {FieldConfig} from "../shared/model/form/field.interface";

export const defaultBookManagementRequestConfiguration: FieldConfig[] = [
  {
    type: 'select',
    label: 'Book',
    name: 'book',
    value: 'book',
    options: ['neko', 'nesto'],
    showButton: true,
  },
  {
    type: 'input',
    label: 'Quantity',
    inputType: 'text',
    name: 'quantity',
    validations: []
  },
  {
    type: 'input',
    label: 'Reason',
    inputType: 'text',
    name: 'reason',
    validations: []
  },
  {
    type: 'button',
    name: 'saveBookRequest',
    label: 'Save'
  }
]
