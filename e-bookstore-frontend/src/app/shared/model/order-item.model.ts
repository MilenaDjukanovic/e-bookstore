export interface IBaseOrderItem {
  bookId: number;
  quantity: number;
}

export interface ICreateOrderItem extends IBaseOrderItem {}

export class CreateOrderItem implements ICreateOrderItem {
  constructor(public bookId: number,
              public quantity: number) {
  }
}
