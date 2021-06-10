import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FieldConfig} from "../../../model/form/field.interface";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {DynamicFormService} from "../../../../services/dynamic-form.service";

export interface DialogData {
  fieldConfiguration: FieldConfig[];
}

@Component({
  selector: 'app-dynamic-form',
  templateUrl: './dynamic-form.component.html',
  styleUrls: ['./dynamic-form.component.css']
})
export class DynamicFormComponent implements OnInit {

  @Input() fields: FieldConfig[] = [];
  @Input() error!: string;

  @Output() submit: EventEmitter<any> = new EventEmitter<any>();
  @Output() actionCalled: EventEmitter<any> = new EventEmitter<any>();

  form!: FormGroup;

  constructor(private formBuilder: FormBuilder, private dynamicFormService: DynamicFormService) {
  }

  ngOnInit(): void {
    this.form = this.createControl();
    this.dynamicFormService.onButtonAction.subscribe(data => {
        this.actionCalled.emit(data)
      }
    )
  }

  public getValue() {
    return this.form.value;
  }

  public createControl() {
    const group = this.formBuilder.group({});
    this.fields.forEach(field => {
      if (field.type === "button") {
        return;
      }

      const control = this.formBuilder.control(
        field.value,
        field.validations || []
      );
      if (field.name != null) {
        group.addControl(field.name, control);
      }
    });

    return group;
  }


  public bindValidations(validations: any) {
    if (validations.length > 0) {
      let validList: any[] = [];
      // @ts-ignore
      validations.forEach(valid => {
        validList.push(valid.validators)
      });
      return Validators.compose(validList);
    }

    return null;
  }

  public onSubmit(event: Event) {
    event.preventDefault();
    event.stopPropagation();

    if (this.form.valid) {
      this.submit.emit(this.form.value);
      this.form.reset();
    } else {
      this.validateAllFormFields(this.form);
    }
  }

  public validateAllFormFields(formGroup: FormGroup) {
    Object.keys(formGroup.controls).forEach(field => {
      const control = formGroup.get(field);
      control?.markAsTouched({onlySelf: true});
    })
  }

}
