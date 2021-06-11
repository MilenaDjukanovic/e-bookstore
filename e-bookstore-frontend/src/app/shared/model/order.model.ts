import {CreateOrderItem} from "./order-item.model";

export interface IBaseOrder {
  orderItems: Array<CreateOrderItem>;
  address: string;
}

export interface ICreateOrder extends IBaseOrder {}

export class CreateOrder implements ICreateOrder {
  constructor(public orderItems:  Array<CreateOrderItem>,
              public address: string) {
  }
}
