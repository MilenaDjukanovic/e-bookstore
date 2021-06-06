import { Component, OnInit } from '@angular/core';
import {BookManagementRequest} from "../../shared/model/book-management-requests.model";
import {Pageable} from "../../shared/util/request.utils";
import {BookManagementRequestService} from "../../services/book-management-request.service";

@Component({
  selector: 'app-manage-book-requests',
  templateUrl: './manage-book-requests.component.html',
  styleUrls: ['./manage-book-requests.component.css']
})
export class ManageBookRequestsComponent implements OnInit {

  public bookManagementRequests!: BookManagementRequest[];

  private pageable!: Pageable;
  public numberOfElements!: number;
  public pageSize!: number;

  public columnDefinition: Array<any> = new Array<any>();
  public defaultActionDefinition: Array<any> = new Array<any>();

  constructor(private bookManagementRequestService: BookManagementRequestService) { }

  ngOnInit(): void {
    this.getFirstPendingBookManagementRequests();
    this.initializeDefaultColumnDefinitions();
    this.initializeDefaultActions();
  }

  private getFirstPendingBookManagementRequests() {
    this.pageable = new Pageable(0,10);
    this.bookManagementRequestService.getAllPendingBookManagementRequests(false, this.pageable)
      .subscribe(data => {
        this.bookManagementRequests = data.content;
        this.pageSize = data.pageable.pageSize;
        this.numberOfElements = data.totalElements;
      })
  }

  public loadPendingRequests(event: any) {
    this.pageable = new Pageable(event.pageIndex, this.pageSize);
    this.bookManagementRequestService.getAllPendingBookManagementRequests(false, this.pageable)
      .subscribe(data =>(
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
      },{
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

  onAction($event: any) {

  }
}
