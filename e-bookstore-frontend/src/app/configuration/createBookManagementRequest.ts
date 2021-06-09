import { FieldConfig } from "../shared/model/form/field.interface";
import { PublishingHouseApi } from "./api/publishing-house-api";

export const defaultBookManagementRequestConfiguration: FieldConfig[] = [{
  type: 'rest-select',
  name: 'book',
  label: 'Book',
  optionsUrl: PublishingHouseApi.private.findAllBooks,
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
