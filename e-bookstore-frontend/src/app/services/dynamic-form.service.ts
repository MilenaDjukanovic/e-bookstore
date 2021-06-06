import {Injectable} from '@angular/core';
import {Subject} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class DynamicFormService {

  public onButtonAction: Subject<any> = new Subject<any>();

  constructor() {
  }

  public handleButtonAction(buttonAction: string): void {
    this.onButtonAction.next(buttonAction);
  }
}
