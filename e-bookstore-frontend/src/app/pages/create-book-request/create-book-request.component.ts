import {Component, OnInit, ViewChild} from '@angular/core';
import {DynamicFormComponent} from "../../shared/components/form/dynamic-form/dynamic-form.component";
import {FieldConfig} from "../../shared/model/form/field.interface";
import {
  createExistingBookManagementRequestConfiguration
} from "../../configuration/createBookConfiguration";
import {AuthorDialogComponent} from "../../shared/components/author-dialog/author-dialog.component";
import {ComponentType} from "@angular/cdk/overlay";
import {MatDialog} from "@angular/material/dialog";
import {BookManagementRequest} from "../../shared/model/book-management-requests.model";
import {Pageable} from "../../shared/util/request.utils";
import {BookManagementRequestService} from "../../services/book-management-request.service";
import {AuthService} from "../../services/authority/auth.service";


@Component({
  selector: 'app-create-book-request',
  templateUrl: './create-book-request.component.html',
  styleUrls: ['./create-book-request.component.css']
})
export class CreateBookRequestComponent implements OnInit {

  @ViewChild(DynamicFormComponent) form!: DynamicFormComponent;

  public createBook: FieldConfig[] = createExistingBookManagementRequestConfiguration;

  public showFormForExistingBooks = false;
  public showFormForNewBooks = false;

  public bookManagementRequests!: BookManagementRequest[];

  private pageable!: Pageable;
  public numberOfElements!: number;
  public pageSize!: number;

  public columnDefinition: Array<any> = new Array<any>();

  public submit(value: any) {
    console.log(value);
  }
  constructor( public dialog: MatDialog,
               private bookManagementRequestService: BookManagementRequestService,
               private authService: AuthService) { }

  ngOnInit(): void {
    this.getFirstPendingBookManagementRequests();
    this.initializeDefaultColumnDefinitions();
  }

  handleAction($event: any) {
    if($event === 'createAuthor') {
      this.openDialog(AuthorDialogComponent );
    }
  }

  public openDialog(dialogToOpen: ComponentType<any>): void {
    const dialogRef = this.dialog.open(AuthorDialogComponent);
  }

  public showFormWithExistingBooks():void {
    this.showFormForExistingBooks = true;
    this.showFormForNewBooks = false;
  }

  public showFormWithNewBooks():void {
    this.showFormForExistingBooks = false;
    this.showFormForNewBooks = true;
  }

  private getFirstPendingBookManagementRequests() {
    this.pageable = new Pageable(0,5);
    this.bookManagementRequestService
      .getBookManagementRequestsByProcessedAndByPublishingHouse(this.pageable,
        this.authService.getCurrentUserValue().id, false).subscribe(
          data => {
            this.bookManagementRequests = data.content;
            this.pageSize = data.pageable.pageSize;
            this.numberOfElements = data.totalElements;
          }
    );
  }

  public loadPendingRequests(event: any) {
    this.pageable = new Pageable(event.pageIndex, this.pageSize);
    this.bookManagementRequestService
      .getBookManagementRequestsByProcessedAndByPublishingHouse(this.pageable,
        this.authService.getCurrentUserValue().id, false).subscribe(
      data => {
        this.bookManagementRequests = data.content;
      }
    );

  }

  public initializeDefaultColumnDefinitions(): void {
    this.columnDefinition = [{
      label: 'Book',
      property: 'book.title'
    },
      {
        label: 'Quantity',
        property: 'quantity'
      },
      {
        label: 'Processed',
        property: 'processed'
      },
      {
        label: 'Reason',
        property: 'reason'
      }
    ];
  }

}
