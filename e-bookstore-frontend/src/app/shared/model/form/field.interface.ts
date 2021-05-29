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
  serviceMethod?: any;
  collections?: any;
  type: string;
  value?: any;
  validations?: Validator[];
}

