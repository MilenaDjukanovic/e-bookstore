import {Injectable} from '@angular/core';
import {BookPurchase} from "../../shared/model/book-purchase.model";
import {Book} from "../../shared/model/book.model";

@Injectable({
  providedIn: 'root'
})
export class BookPurchasesService {

  private booksForPurchase: Array<BookPurchase> = new Array<BookPurchase>();

  constructor() {
  }

  public getBooksForPurchase() {
    return this.booksForPurchase;
  }

  public addBook(bookForPurchase: BookPurchase): void {
    for (let bookPurchase of this.booksForPurchase) {
      if (bookPurchase.quantity && bookForPurchase.quantity && bookPurchase.book?.id === bookForPurchase.book?.id) {
        bookPurchase.quantity += bookForPurchase.quantity;

        if (bookPurchase.book) {
          bookPurchase.totalPrice = Math.round(bookPurchase.quantity * bookPurchase.book?.price);
          this.decreaseBookQuantity(bookPurchase.book, bookForPurchase.quantity);
        }
        return;
      }
    }

    if (bookForPurchase.book && bookForPurchase.quantity) {
      bookForPurchase.totalPrice = Math.round(bookForPurchase.book?.price * bookForPurchase.quantity);
      this.decreaseBookQuantity(bookForPurchase.book, bookForPurchase.quantity);
    }

    this.booksForPurchase.push(bookForPurchase);
  }

  public removeBook(index: number) {
    const bookPurchase = this.booksForPurchase[index];
    if (bookPurchase && bookPurchase.book && bookPurchase.quantity) {
      this.increaseBookQuantity(bookPurchase.book, bookPurchase.quantity);
    }
    this.booksForPurchase.splice(index, 1);
    return this.booksForPurchase;
  }

  public increaseBookQuantity(book: Book, quantity: number): void {
    if (book && quantity) {
      book.inStock += quantity;
    }
  }

  public decreaseBookQuantity(book: Book, quantity: number): void {
    debugger
    if (book && quantity) {
      book.inStock -= quantity;
    }
  }
}
