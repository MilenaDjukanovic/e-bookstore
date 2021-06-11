import { Validators } from "@angular/forms";
import {FieldConfig} from "../shared/model/form/field.interface";

export const orderDetailsConfiguration: FieldConfig[] = [
  {
    type: 'input',
    label: 'Address',
    inputType: 'text',
    name: 'address',
    validations: [Validators.required],
    error: 'Address cannot be empty!'
  },
  {
    type: 'button',
    name: 'save',
    label: 'Make reservation'
  }
]
