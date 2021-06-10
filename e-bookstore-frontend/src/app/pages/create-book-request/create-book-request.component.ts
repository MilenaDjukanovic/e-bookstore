import {Component, OnInit, ViewChild} from '@angular/core';
import {DynamicFormComponent} from "../../shared/components/form/dynamic-form/dynamic-form.component";
import {FieldConfig} from "../../shared/model/form/field.interface";
import {AuthorDialogComponent} from "../../shared/components/author-dialog/author-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {BookManagementRequest, CreateBookManagementRequest} from "../../shared/model/book-management-requests.model";
import {Pageable} from "../../shared/util/request.utils";
import {BookManagementRequestService} from "../../services/core/book-management-request.service";
import {AuthService} from "../../services/authority/auth.service";
import {defaultBookManagementRequestConfiguration} from "../../configuration/createBookManagementRequest";
import {defaultBookConfiguration} from "../../configuration/createBookConfiguration";
import {CategoryDialogComponent} from "../../shared/components/category-dialog/category-dialog.component";
import {IBaseAuthor} from "../../shared/model/author.model";
import {IBaseCategory} from "../../shared/model/category.model";
import {CreateBook} from "../../shared/model/book.model";
import {BookService} from "../../services/core/book.service";


@Component({
  selector: 'app-create-book-request',
  templateUrl: './create-book-request.component.html',
  styleUrls: ['./create-book-request.component.css']
})
export class CreateBookRequestComponent implements OnInit {

  @ViewChild(DynamicFormComponent) form!: DynamicFormComponent;

  public fieldConfigs!: FieldConfig[];

  public showFormForBookRequests = false;
  public showFormForNewBooks = false;

  public bookManagementRequests!: BookManagementRequest[];
  public numberOfElements!: number;
  public pageSize!: number;
  public columnDefinition: Array<any> = new Array<any>();

  public createBookError!: string;
  public createBookRequestError!: string;

  private pageable!: Pageable;
  private pageIndex!: number;
  private author!: IBaseAuthor;
  private category!: IBaseCategory;

  constructor(public dialog: MatDialog,
              private bookManagementRequestService: BookManagementRequestService,
              private authService: AuthService,
              private bookService: BookService) {
  }

  public createBookRequest(bookRequest: CreateBookManagementRequest) {
    this.bookManagementRequestService.createBookRequestService(bookRequest).subscribe(data => {
      this.createBookRequestError = '';
      this.refreshTable();
    }, error => {
      this.createBookRequestError = error;
    });
  }

  public createBook(book: CreateBook) {
    this.bookService.createBook(book).subscribe(data => {
      this.createBookError = '';
    }, error => {
      this.createBookError = error;
    });
  }

  ngOnInit(): void {
    this.getFirstPendingBookManagementRequests();
    this.initializeDefaultColumnDefinitions();
  }

  public handleAction($event: any) {
    if ($event === 'createAuthor') {
      this.openAuthorDialog();
    }
    if ($event === 'createCategory') {
      this.openCategoryDialog();
    }
  }

  public openAuthorDialog(): void {
    const dialogRef = this.dialog.open(AuthorDialogComponent);
    dialogRef.afterClosed().subscribe(result => {
      this.author = result;
    })
  }

  public openCategoryDialog(): void {
    const dialogRef = this.dialog.open(CategoryDialogComponent);
    dialogRef.afterClosed().subscribe(result => {
      this.category = result;
    })
  }

  public showFormForCreatingBookRequests(): void {
    this.showFormForBookRequests = true;
    this.showFormForNewBooks = false;
    this.fieldConfigs = defaultBookManagementRequestConfiguration;
  }

  public showFormForCreatingBook(): void {
    this.showFormForBookRequests = false;
    this.showFormForNewBooks = true;
    this.fieldConfigs = defaultBookConfiguration;
  }

  public loadPendingRequests(event: any) {
    this.pageable = new Pageable(event.pageIndex, this.pageSize);
    this.bookManagementRequestService
      .getBookManagementRequestsByProcessedAndByPublishingHouse(this.pageable,
        this.authService.getCurrentUserValue().id, false).subscribe(
      data => {
        this.bookManagementRequests = data.content;
        this.pageIndex = event.pageIndex ? event.pageIndex : 0;
        this.numberOfElements = data.totalElements;
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

  private getFirstPendingBookManagementRequests() {
    this.pageable = new Pageable(0, 5);
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

  private refreshTable() {
    const pageToGet = {
      pageIndex: this.pageIndex,
      pageSize: this.pageSize
    }
    this.loadPendingRequests(pageToGet);
  }

}
