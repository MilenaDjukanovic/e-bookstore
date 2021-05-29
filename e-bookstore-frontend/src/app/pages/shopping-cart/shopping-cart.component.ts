import {Component, OnInit} from '@angular/core';
import {BookPurchase} from "../../shared/model/book-purchase.model";
import {BookPurchasesService} from "../../services/book-purchases.service";
import {Book} from "../../shared/model/book.model";
import {Router} from "@angular/router";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  public booksForPurchase!: BookPurchase[];

  public dataSource = new MatTableDataSource();

  public displayedColumns: string[] = ['name', 'author', 'quantity', 'price', 'remove-from-cart'];

  public totalCost = 0;

  constructor(private bookPurchaseService: BookPurchasesService, private router: Router) {
    this.booksForPurchase = this.bookPurchaseService.getBooksForPurchase();
    this.refreshDataSource();

    if (this.booksForPurchase) {
      this.booksForPurchase.forEach(bookPurchase => {
        // @ts-ignore
        this.totalCost = this.totalCost + bookPurchase.book?.price;
      })
    }
  }

  ngOnInit(): void {
  }

  getTotalCost() {
    return this.totalCost;
  }

  public navigateToBooks():void {
      this.router.navigate(['/']);
  }

  public deleteBookPurchase(bookPurchase: BookPurchase, index: number) {
    debugger
    // @ts-ignore
    this.totalCost = this.totalCost - bookPurchase.book.price;
    this.booksForPurchase = this.bookPurchaseService.removeBook(index);

    this.refreshDataSource();
  }

  private refreshDataSource(){
    this.dataSource = new MatTableDataSource<any>(this.booksForPurchase);
  }
}
