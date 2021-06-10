import { HttpClient } from "@angular/common/http";
import { Component, OnInit } from '@angular/core';
import { FormGroup } from "@angular/forms";
import { MatDialog} from "@angular/material/dialog";
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

  constructor(private httpClient: HttpClient,
              private dialog: MatDialog) { }

  ngOnInit(): void {
    this.initializeControlValues();
  }

  public onCreateEntity() {
    if(this.field.entityCreateComponentDialog){
      const dialogRef = this.dialog.open(this.field.entityCreateComponentDialog);
      dialogRef.afterClosed().subscribe(result => {
        this.initializeControlValues();
      })
    }
  }

  private initializeControlValues(): void{
    const url = this.field['optionsUrl'];
    if(url){
      this.httpClient.get(url).subscribe(data => {
        this.parseOptions(data)
      }, error => {
        console.error(error)
      });
    }
  }

  private parseOptions(rawOptions: any): void{
    const tempOptions = new Array<any>();
    if(this.field['optionsArrayProperty']){
      const values: Array<any> = rawOptions[this.field['optionsArrayProperty']];
      values.forEach(value => {
        if(this.field['optionsDisplayProperty'] && this.field['optionsValueProperty']){
          const fieldDisplayValue = this.resolveDisplayValue(value);
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

  private resolveDisplayValue(value: any): string{
    const optionsDisplayComplexProperty = this.field['optionsDisplayComplexProperty'];
    if(optionsDisplayComplexProperty){
      const properties = optionsDisplayComplexProperty['properties'];
      let format = optionsDisplayComplexProperty['format'];

      properties.forEach(property => {
        if(property){
          const toReplace = '{' + property + '}';
          format = format.replace(toReplace, value[property])
        }
      })

      return format;
    }

    return this.field['optionsDisplayProperty'] ? value[this.field['optionsDisplayProperty']] : '';
  }

}
