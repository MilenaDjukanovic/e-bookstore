import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FieldConfig} from "../../../model/form/field.interface";
import {FormGroup} from "@angular/forms";
import {DynamicFormService} from "../../../../services/dynamic-form.service";

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.css']
})
export class SelectComponent implements OnInit {

  public field!: FieldConfig;
  public group!: FormGroup;

  constructor(private dynamicFormService: DynamicFormService) { }

  ngOnInit(): void {
  }

  public onAction() {
    if (this.field.actionName != null) {
      this.dynamicFormService.handleButtonAction(this.field.actionName);
    }
  }
}
