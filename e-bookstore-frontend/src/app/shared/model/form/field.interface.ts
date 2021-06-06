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
  showButton?: boolean;
  type: string;
  value?: any;
  actionName?: string;
  validations?: Validator[];
}

