import {HttpClient} from "@angular/common/http";
import {Injectable, OnInit} from '@angular/core';
import {BookPurchase} from "../../shared/model/book-purchase.model";
import {Book} from "../../shared/model/book.model"
import {CreateOrderItem} from "../../shared/model/order-item.model";
import {CreateOrder} from "../../shared/model/order.model";
import {OrdersApi} from "../../configuration/api/orders-api";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BookPurchasesService implements OnInit {

  private booksForPurchase: Array<BookPurchase> = new Array<BookPurchase>();
  public readonly BOOK_CART_ITEM = 'bookCartItem';

  constructor(private httpClient: HttpClient) {
  }

  ngOnInit(): void {
    const bookCart = localStorage.getItem(this.BOOK_CART_ITEM);
    if (bookCart) {
      this.booksForPurchase = JSON.parse(bookCart);
    }
  }

  public getBooksForPurchase() {
    const bookCart = localStorage.getItem(this.BOOK_CART_ITEM);
    if (bookCart) {
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

  public createOrder(address: string): Observable<any>{
    const orderToCreate = this.createOrderItems(address)
    const url = OrdersApi.public.purchase;
    return this.httpClient.post(url, orderToCreate);
  }

  private createOrderItems(address: string): CreateOrder {
    const bookCart = localStorage.getItem(this.BOOK_CART_ITEM);
    const order: CreateOrder = new CreateOrder(new Array<CreateOrderItem>(), address);
    if(bookCart) {
      const booksToBuy = JSON.parse(bookCart);
      booksToBuy.forEach((bookToBuy: BookPurchase) => {
        if (bookToBuy.book?.id && bookToBuy.quantity) {
          const orderItem = new CreateOrderItem(bookToBuy.book?.id, bookToBuy.quantity);
          order.orderItems.push(orderItem);
        }
      })
    }
    return order;
  }
}
