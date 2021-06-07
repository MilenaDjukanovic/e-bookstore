import { HttpClient } from "@angular/common/http";
import { Component, OnInit } from '@angular/core';
import { FormGroup } from "@angular/forms";
import { DynamicFormService } from "../../../../services/dynamic-form.service";
import { FieldConfig } from "../../../model/form/field.interface";

@Component({
  selector: 'app-rest-select',
  templateUrl: './rest-select.component.html',
  styleUrls: ['./rest-select.component.css']
})
export class RestSelectComponent implements OnInit {

  public field!: FieldConfig;
  public group!: FormGroup;

  public options: Array<any> = new Array<any>();

  constructor(private dynamicFormService: DynamicFormService, private httpClient: HttpClient) { }

  ngOnInit(): void {
    this.initializeControlValues();
  }

  public onAction() {
    if (this.field.actionName != null) {
      this.dynamicFormService.handleButtonAction(this.field.actionName);
    }
  }

  private initializeControlValues(): void{
    const url = this.field['optionsUrl'];
    if(url){
      this.httpClient.get(url).subscribe(data => {
        this.parseOptions(data)
      }, error => {
        // #TODO handle error
        console.log(error)
      });
    }
  }

  private parseOptions(rawOptions: any): void{
    const tempOptions = new Array<any>();
    if(this.field['optionsArrayProperty']){
      const values: Array<any> = rawOptions[this.field['optionsArrayProperty']];
      values.forEach(value => {
        if(this.field['optionsDisplayProperty'] && this.field['optionsValueProperty']){
          const fieldDisplayValue = value[this.field['optionsDisplayProperty']];
          const fieldValue = value[this.field['optionsValueProperty']];
          tempOptions.push({
            displayValue: fieldDisplayValue,
            value: fieldValue
          })
        }
      })
    }

    this.options = tempOptions;
  }

}
