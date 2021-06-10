import { Validators } from "@angular/forms";
import { FieldConfig } from "../shared/model/form/field.interface";
import { PublishingHouseApi } from "./api/publishing-house-api";

export const defaultBookManagementRequestConfiguration: FieldConfig[] = [{
  type: 'rest-select',
  name: 'bookId',
  label: 'Book',
  optionsUrl: PublishingHouseApi.private.findAllBooks,
  optionsArrayProperty: 'content',
  optionsDisplayProperty: 'title',
  optionsDisplayComplexProperty: {
    properties: ['title', 'inStock'],
    format: '{title} (In Stock: {inStock})'
  },
  optionsValueProperty: 'id',
  validations: [Validators.required],
  error: 'Book cannot be empty!'
}, {
  type: 'input',
  label: 'Quantity',
  inputType: 'number',
  name: 'quantity',
  validations: [Validators.required],
  error: 'Quantity cannot be empty or less than 1!'
}, {
  type: 'input',
  label: 'Reason',
  inputType: 'text',
  name: 'reason',
  validations: [Validators.required],
  error: 'Please provide the reason for your request'
}, {
  type: 'button',
  name: 'saveBookRequest',
  label: 'Save'
}];
