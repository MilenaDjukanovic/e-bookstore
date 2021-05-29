import { Component, OnInit } from '@angular/core';
import {BookService} from "../../services/book.service";
import {Book} from "../../shared/model/book.model";
import {Pageable} from "../../shared/util/request.utils";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public books!: Book[];

  public numberOfItems!: number;
  public pageSize!: number;

  private pageable!: Pageable;

  constructor(private bookService: BookService) {
  }

  ngOnInit(): void {
    this.pageable = new Pageable(0, 2);
    this.bookService.getBooks(this.pageable).subscribe(data => {
        this.books = data.content;
        this.numberOfItems = data.totalElements;
        this.pageSize = data.pageable.pageSize;
      }
    )
  }

  public loadBooks(event: any) {
    this.pageable = new Pageable(event.pageIndex, this.pageSize);
    this.bookService.getBooks(this.pageable).subscribe(data => {
        this.books = data.content;
        this.numberOfItems = data.totalElements;
      }
    )
  }
}
