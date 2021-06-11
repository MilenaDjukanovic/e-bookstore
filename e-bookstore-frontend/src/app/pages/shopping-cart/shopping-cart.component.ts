import {Component, OnInit} from '@angular/core';
import {BookPurchase} from "../../shared/model/book-purchase.model";
import {Router} from "@angular/router";
import {MatTableDataSource} from "@angular/material/table";
import {BookPurchasesService} from "../../services/core/book-purchases.service";

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  public booksForPurchase!: BookPurchase[];

  public dataSource = new MatTableDataSource();

  public totalCost = 0;

  public columnDefinitions: Array<any> = new Array<any>();

  public defaultActions: Array<any> = new Array<any>();

  constructor(private bookPurchaseService: BookPurchasesService, private router: Router) {
    // this.refreshDataSource();
  }

  ngOnInit(): void {
    this.initializeDefaultColumnDefinitions();
    this.initializeDefaultActionsDefinition();
    this.booksForPurchase = this.bookPurchaseService.getBooksForPurchase();
    if (this.booksForPurchase) {
      this.booksForPurchase.forEach(bookPurchase => {
        // @ts-ignore
        this.totalCost = this.totalCost + bookPurchase.book?.price;
      })
    }
  }

  getTotalCost() {
    return this.totalCost;
  }

  public navigateToBooks(): void {
    this.router.navigate(['/']);
  }

  public initializeDefaultColumnDefinitions(): void {
    this.columnDefinitions = [{
      label: 'Name',
      property: 'book.title'
    }, {
      label: 'Author',
      property: 'book.author.firstName'
    }, {
      label: 'Quantity',
      property: 'quantity'
    }, {
      label: 'Price',
      property: 'totalPrice'
    }];
  }

  public initializeDefaultActionsDefinition(): void {
    this.defaultActions = [
      {
        label: 'Remove',
        actionName: 'remove',
        icon: 'delete'
      }
    ]
  }

  public deleteBookPurchase($event: any) {
    if ($event.actionName == 'remove') {
      const bookPurchase = $event.element;
      this.totalCost = this.totalCost - bookPurchase.book.price;
      this.booksForPurchase = Object.assign([], this.bookPurchaseService.removeBook($event.index));
    }
  }

  public reserveBooks(): void {

  }
}
