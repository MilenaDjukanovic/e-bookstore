import {FieldConfig} from "../shared/model/form/field.interface";
import {PublishingHouseApi} from "./api/publishing-house-api";
import {AuthorsApi} from "./api/authors-api";
import {AuthorDialogComponent} from "../shared/components/author-dialog/author-dialog.component";
import {CategoryApi} from "./api/category-api";
import {CategoryDialogComponent} from "../shared/components/category-dialog/category-dialog.component";

export const defaultBookSearch: FieldConfig[] = [
  {
    type: 'rest-select',
    label: 'Category',
    name: 'categoryId',
    optionsUrl: CategoryApi.public.findAllNoLimit,
    optionsArrayProperty: 'content',
    optionsDisplayProperty: 'name',
    optionsValueProperty: 'id',
    showButton: false
  },
  {
  type: 'input',
  label: 'Book title',
  inputType: 'text',
  name: 'title',
  validations: []
}, {
  type: 'button',
  name: 'saveBookRequest',
  label: 'Search'
}];
