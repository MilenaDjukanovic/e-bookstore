import {Component, Input, OnInit} from '@angular/core';
import {Book} from "../../model/book.model";
import {BookPurchasesService} from "../../../services/book-purchases.service";
import {BookPurchase} from "../../model/book-purchase.model";

@Component({
  selector: 'app-book-card',
  templateUrl: './book-card.component.html',
  styleUrls: ['./book-card.component.css']
})
export class BookCardComponent implements OnInit {

  @Input() book!: Book;

  public stars = [1, 2, 3, 4, 5];

  public quantityToBuy = 1;

  constructor(private bookPurchasesService: BookPurchasesService) {
  }

  ngOnInit(): void {
  }

  public reduceQuantity() {
    if (this.quantityToBuy > 1) {
      this.quantityToBuy--;
    }
  }

  public increaseQuantity() {
    if (this.quantityToBuy < this.book.inStock) {
      this.quantityToBuy++;
    }
  }

  public addToCart() {
    const bookPurchase = new BookPurchase(this.book, this.quantityToBuy);
    this.bookPurchasesService.addBook(bookPurchase);
    this.book.inStock = this.book.inStock - this.quantityToBuy;
  }
}
