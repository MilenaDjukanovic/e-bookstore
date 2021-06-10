import { Validators } from "@angular/forms";
import {FieldConfig} from "../shared/model/form/field.interface";

export const authorDialogConfiguration: FieldConfig[] = [
  {
    type: 'input',
    label: 'First Name',
    inputType: 'text',
    name: 'firstName',
    validations: [Validators.required],
    error: 'First Name cannot be empty!'
  },
  {
    type: 'input',
    label: 'Last Name',
    inputType: 'text',
    name: 'lastName',
    validations: [Validators.required],
    error: 'Last Name cannot be empty!'
  },
  {
    type: 'input',
    label: 'Birth Year',
    inputType: 'number',
    name: 'birthYear',
    validations: [Validators.required, Validators.min(0)],
    error: 'Birth year cannot be empty or 0!'
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
