import {Injectable, OnInit} from '@angular/core';
import {BookPurchase} from "../../shared/model/book-purchase.model";
import {Book} from "../../shared/model/book.model";

@Injectable({
  providedIn: 'root'
})
export class BookPurchasesService implements OnInit{

  private booksForPurchase: Array<BookPurchase> = new Array<BookPurchase>();
  private readonly BOOK_CART_ITEM = 'bookCartItem';

  constructor() {
  }

  ngOnInit(): void {
    const bookCart = localStorage.getItem(this.BOOK_CART_ITEM);
    if(bookCart){
      this.booksForPurchase = JSON.parse(bookCart);
    }
  }

  public getBooksForPurchase() {
    const bookCart = localStorage.getItem(this.BOOK_CART_ITEM);
    if(bookCart) {
      return JSON.parse(bookCart);
    }
  }

  public addBook(bookForPurchase: BookPurchase): void {
    for (let bookPurchase of this.booksForPurchase) {
      if (bookPurchase.quantity && bookForPurchase.quantity && bookPurchase.book?.id === bookForPurchase.book?.id) {
        bookPurchase.quantity += bookForPurchase.quantity;
        this.calculateTotalPrice(bookPurchase);
        this.updateShoppingCart();
        return;
      }
    }
    this.calculateTotalPrice(bookForPurchase);
    this.booksForPurchase.push(bookForPurchase);
    this.updateShoppingCart();
  }

  public removeBook(index: number) {
    const bookPurchase = this.booksForPurchase[index];
    if (bookPurchase && bookPurchase.book && bookPurchase.quantity) {
      this.increaseBookQuantity(bookPurchase.book, bookPurchase.quantity);
    }
    this.booksForPurchase.splice(index, 1);
    this.updateShoppingCart();
    return this.booksForPurchase;
  }

  public increaseBookQuantity(book: Book, quantity: number): void {
    if (book && quantity) {
      book.inStock += quantity;
    }
  }

  private decreaseBookQuantity(book: Book, quantity: number): void {
    if (book && quantity) {
      book.inStock -= quantity;
    }
  }

  private updateShoppingCart() {
    localStorage.setItem(this.BOOK_CART_ITEM, JSON.stringify(this.booksForPurchase));
  }

  private calculateTotalPrice(bookForPurchase: BookPurchase) {
    if (bookForPurchase.book && bookForPurchase.quantity) {
      bookForPurchase.totalPrice = Math.round(bookForPurchase.book?.price * bookForPurchase.quantity);
      this.decreaseBookQuantity(bookForPurchase.book, bookForPurchase.quantity);
    }
  }

}
