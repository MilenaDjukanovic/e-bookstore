import {Injectable} from '@angular/core';
import {BookPurchase} from "../../shared/model/book-purchase.model";

@Injectable({
  providedIn: 'root'
})
export class BookPurchasesService {

  private booksForPurchase: BookPurchase[] = [];

  constructor() {
  }

  public getBooksForPurchase() {
    return this.booksForPurchase;
  }

  public addBook(bookForPurchase: BookPurchase) {
    this.booksForPurchase.push(bookForPurchase);
  }

  public removeBook(index: number) {
     this.booksForPurchase.splice(index, 1);
     return this.booksForPurchase;
  }

}
