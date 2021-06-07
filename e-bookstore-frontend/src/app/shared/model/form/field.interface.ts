import { ComponentType } from "@angular/cdk/overlay";

export interface Validator {
  name: string;
  validator: any;
  message: string;
}

export interface FieldConfig {
  label: string;
  name: string;
  inputType?: string;
  options?: any;
  optionsUrl?: string;
  optionsArrayProperty?: string;
  optionsDisplayProperty?: string;
  optionsValueProperty?: string;
  entityCreateComponentDialog?: ComponentType<any>;
  serviceMethod?: any;
  collections?: any;
  showButton?: boolean;
  type: string;
  value?: any;
  actionName?: string;
  validations?: Validator[];
}

