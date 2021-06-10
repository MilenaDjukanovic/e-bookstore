import { Validators } from "@angular/forms";
import {FieldConfig} from "../shared/model/form/field.interface";

export const categoryDialogConfiguration: FieldConfig[] = [
  {
    type: 'input',
    label: 'Category Name',
    inputType: 'text',
    name: 'name',
    validations: [Validators.required],
    error: 'Category Name cannot be empty!'
  },
  {
    type: 'button',
    name: 'save',
    label: 'Save'
  }
]
