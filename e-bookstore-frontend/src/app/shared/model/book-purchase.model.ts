import {Book, IBook} from "./book.model";

export interface IBookPurchase {
  book?: Book;
  quantity?: number;
  totalPrice?: number;
}

export class BookPurchase implements IBookPurchase {
  constructor(public book?: Book,
              public quantity?: number,
              public totalPrice?: number) {
  }
}
