import { FieldConfig } from "../shared/model/form/field.interface";

export const defaultBookManagementRequestConfiguration: FieldConfig[] = [{
  type: 'rest-select',
  name: 'book',
  label: 'Book',
  optionsUrl: '/spring/api/public/books/all',
  optionsArrayProperty: 'content',
  optionsDisplayProperty: 'title',
  optionsValueProperty: 'id'
}, {
  type: 'input',
  label: 'Quantity',
  inputType: 'text',
  name: 'quantity',
  validations: []
}, {
  type: 'input',
  label: 'Reason',
  inputType: 'text',
  name: 'reason',
  validations: []
}, {
  type: 'button',
  name: 'saveBookRequest',
  label: 'Save'
}];
