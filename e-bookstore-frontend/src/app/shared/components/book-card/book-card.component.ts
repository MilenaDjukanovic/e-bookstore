import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Book} from "../../model/book.model";
import {BookPurchasesService} from "../../../services/core/book-purchases.service";
import {BookPurchase} from "../../model/book-purchase.model";
import {AuthService} from "../../../services/authority/auth.service";
import {PublishingHouseService} from "../../../services/core/publishing-house.service";

@Component({
  selector: 'app-book-card',
  templateUrl: './book-card.component.html',
  styleUrls: ['./book-card.component.css']
})
export class BookCardComponent implements OnInit {

  @Input() book!: Book;
  @Output() deleteBook: EventEmitter<any> = new EventEmitter();

  public representativeLoggedIn: boolean = false;
  public quantityToBuy!: number;

  constructor(private bookPurchasesService: BookPurchasesService,
              private authService: AuthService, private publishingHouseService: PublishingHouseService) {
  }

  ngOnInit(): void {
    this.setBookQuantity();
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
    if(this.quantityToBuy === 0) {
      return;
    }
    const bookPurchase = new BookPurchase(this.book, this.quantityToBuy);
    this.bookPurchasesService.addBook(bookPurchase);
    this.setBookQuantity();
  }

  private setBookQuantity(): void{
    if (this.book.inStock > 0) {
      this.quantityToBuy = 1;
    } else {
      this.quantityToBuy = 0;
    }
  }
}
