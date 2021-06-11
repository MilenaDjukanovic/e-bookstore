import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {DynamicFormComponent} from "../form/dynamic-form/dynamic-form.component";
import {FieldConfig} from "../../model/form/field.interface";
import {MatDialogRef} from "@angular/material/dialog";
import {orderDetailsConfiguration} from "../../../configuration/orderDetailsConfiguration";
import {BookPurchasesService} from "../../../services/core/book-purchases.service";
import {AuthService} from "../../../services/authority/auth.service";
import {Router} from "@angular/router";
import {BookPurchase} from "../../model/book-purchase.model";

@Component({
  selector: 'app-order-dialog',
  templateUrl: './order-dialog.component.html',
  styleUrls: ['./order-dialog.component.css']
})
export class OrderDialogComponent implements OnInit {

  @ViewChild(DynamicFormComponent) form!: DynamicFormComponent;

  public orderDetailsField: FieldConfig[] = orderDetailsConfiguration;
  public error!: string;
  public successful = false;
  public message!: string;

  constructor(public dialogRef: MatDialogRef<OrderDialogComponent>,
              private bookPurchaseService: BookPurchasesService) {
  }

  ngOnInit(): void {
  }

  public submit($event: any) {
      this.reserveBooks($event.address);
  }

  private reserveBooks(address: string) {
    this.bookPurchaseService.createOrder(address).subscribe((data) => {
      this.successful = true;
      this.message = "Successfully ordered books";
      localStorage.setItem(this.bookPurchaseService.BOOK_CART_ITEM, JSON.stringify(new Array<BookPurchase>()));
    }, error => {
      this.error = 'ERROR:' + error;
    })
  }

  public closeDialRef() {
    this.dialogRef.close();
  }
}
