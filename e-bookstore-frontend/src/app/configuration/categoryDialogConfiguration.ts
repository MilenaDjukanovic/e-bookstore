import {FieldConfig} from "../shared/model/form/field.interface";

export const categoryDialogConfiguration: FieldConfig[] = [
  {
    type: 'input',
    label: 'Category name',
    inputType: 'text',
    name: 'name',
    validations: []
  },
  {
    type: 'button',
    name: 'save',
    label: 'Save'
  }
]
