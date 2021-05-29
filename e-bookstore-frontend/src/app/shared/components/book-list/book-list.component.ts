import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Book} from "../../model/book.model";
import {PageEvent} from "@angular/material/paginator";
import {IPageable, Pageable} from "../../util/request.utils";

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {

  @Output()
  pageChangedEvent: EventEmitter<any> = new EventEmitter<any>();

  @Input() public books!: Book[];

  @Input() public totalBooks!: number;

  @Input() public pageSize!: number;

  @Input() public paging!: Pageable;

  constructor() { }

  ngOnInit(): void {
  }

  public onPageChanged(event: any): void {
      this.pageChangedEvent.emit(event);
  }
}
