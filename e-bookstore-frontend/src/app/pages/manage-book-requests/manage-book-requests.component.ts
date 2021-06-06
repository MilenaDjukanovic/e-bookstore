import {Component, OnInit} from '@angular/core';
import {
  BookManagementRequest,
  IBookManagementRequest,
  UpdateBookManagementRequest
} from "../../shared/model/book-management-requests.model";
import {Pageable} from "../../shared/util/request.utils";
import {BookManagementRequestService} from "../../services/core/book-management-request.service";

@Component({
  selector: 'app-manage-book-requests',
  templateUrl: './manage-book-requests.component.html',
  styleUrls: ['./manage-book-requests.component.css']
})
export class ManageBookRequestsComponent implements OnInit {

  public bookManagementRequests!: BookManagementRequest[];
  public numberOfElements!: number;
  public pageSize!: number;
  public pageIndex!: number;
  public columnDefinition: Array<any> = new Array<any>();
  public defaultActionDefinition: Array<any> = new Array<any>();
  private pageable!: Pageable;

  constructor(private bookManagementRequestService: BookManagementRequestService) {
  }

  ngOnInit(): void {
    this.getFirstPendingBookManagementRequests();
    this.initializeDefaultColumnDefinitions();
    this.initializeDefaultActions();
  }

  public loadPendingRequests(event: any) {
    this.pageable = new Pageable(event.pageIndex, this.pageSize);
    this.bookManagementRequestService.getAllPendingBookManagementRequests(false, this.pageable)
      .subscribe(data => (
          this.pageIndex = event.pageIndex,
          this.bookManagementRequests = data.content
      ));
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
      }, {
        label: 'Publishing house',
        property: 'publishingHouse.companyName'
      }]
  }

  public initializeDefaultActions(): void {
    this.defaultActionDefinition = [
      {
        label: 'Approve',
        actionName: 'approve',
        icon: 'check_circle'
      },
      {
        label: 'Reject',
        actionName: 'reject',
        icon: 'cancel'
      }
    ];
  }

  public onAction($event: any) {
    debugger
    if ($event.actionName == 'reject') {
      this.rejectBookManagementRequest($event.element);
    } else if ($event.actionName == 'approve') {
      this.approveBookManagementRequest($event.element);
    }
  }

  private getFirstPendingBookManagementRequests() {
    this.pageable = new Pageable(0, 10);
    this.bookManagementRequestService.getAllPendingBookManagementRequests(false, this.pageable)
      .subscribe(data => {
        this.bookManagementRequests = data.content;
        this.pageSize = data.pageable.pageSize;
        this.numberOfElements = data.totalElements;
      })
  }

  private approveBookManagementRequest(bookManagementRequest: BookManagementRequest) {
    debugger
    const updateBookManagementRequest =
      new UpdateBookManagementRequest(bookManagementRequest.id, bookManagementRequest.book.id,
        bookManagementRequest.quantity, null, bookManagementRequest.reason, bookManagementRequest.processed,
        bookManagementRequest.publishingHouse.id);
    this.bookManagementRequestService.approveBookManagementRequest(updateBookManagementRequest).
    subscribe((data) => {
      console.log('successful');
      this.refreshTable();
    }, (error => {
      console.log(error);
    }))
  }

  private rejectBookManagementRequest(bookManagementRequest: BookManagementRequest) {
    debugger
    this.bookManagementRequestService.rejectBookManagementRequest(bookManagementRequest.id)
      .subscribe(() => {
      this.refreshTable();
    });
  }

  private refreshTable(): void {
    const pageToGet = {
      pageIndex: this.pageIndex,
      pageSize: this.pageSize
    }
    this.loadPendingRequests(pageToGet);
  }
}
