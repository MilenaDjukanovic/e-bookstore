import { ComponentType } from "@angular/cdk/overlay";
import { ValidatorFn} from "@angular/forms";

interface OptionsDisplayComplexProperty{
  properties: Array<string>,
  format: string
}

export interface FieldConfig {
  label: string;
  name: string;
  inputType?: string;
  options?: any;
  optionsUrl?: string;
  optionsArrayProperty?: string;
  optionsDisplayProperty?: string;
  optionsDisplayComplexProperty?: OptionsDisplayComplexProperty;
  optionsValueProperty?: string;
  entityCreateComponentDialog?: ComponentType<any>;
  serviceMethod?: any;
  collections?: any;
  showButton?: boolean;
  type: string;
  value?: any;
  actionName?: string;
  validations?: ValidatorFn[];
  error?: string;
}

