import {FieldConfig} from "../shared/model/form/field.interface";

export const authorDialogConfiguration: FieldConfig[] = [
  {
    type: 'input',
    label: 'First name',
    inputType: 'text',
    name: 'firstname',
    validations: []
  },
  {
    type: 'input',
    label: 'First name',
    inputType: 'text',
    name: 'lastname',
    validations: []
  },
  {
    type: 'input',
    label: 'About',
    inputType: 'text',
    name: 'about',
    validations: []
  },
  {
    type: 'button',
    name: 'save',
    label: 'Save'
  }

]
