import {ComponentFactoryResolver, Directive, Input, OnInit, ViewContainerRef} from '@angular/core';
import {InputComponent} from "../input/input.component";
import {ButtonComponent} from "../button/button.component";
import {SelectComponent} from "../select/select.component";
import {FieldConfig} from "../../../model/form/field.interface";
import {FormGroup} from "@angular/forms";

const componentMapper = {
  input: InputComponent,
  button: ButtonComponent,
  select: SelectComponent
};

@Directive({
  selector: '[dynamicField]'
})
export class DynamicFieldDirective implements OnInit {

  @Input() field!: FieldConfig;

  @Input() group!: FormGroup;

  componentRef: any;

  constructor(private resolver: ComponentFactoryResolver, private container: ViewContainerRef) {
  }

  ngOnInit() {
    // @ts-ignore
    const factory = this.resolver.resolveComponentFactory(componentMapper[this.field.type]);
    this.componentRef = this.container.createComponent(factory);
    this.componentRef.instance.field = this.field;
    this.componentRef.instance.group = this.group;
  }
}
