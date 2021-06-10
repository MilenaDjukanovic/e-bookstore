import { Component, OnInit } from '@angular/core';
import {BookService} from "../../services/core/book.service";
import {Book} from "../../shared/model/book.model";
import {Pageable} from "../../shared/util/request.utils";
import {PublishingHouseService} from "../../services/core/publishing-house.service";
import {FieldConfig} from "../../shared/model/form/field.interface";
import {defaultBookSearch} from "../../configuration/searchBooks";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public books!: Book[];

  public numberOfItems!: number;
  public pageSize!: number;
  private pageIndex!: number;
  private pageable!: Pageable;
  public fieldConfigs: FieldConfig[] = defaultBookSearch;

  constructor(private bookService: BookService, private publishingHouseService: PublishingHouseService) {
  }

  ngOnInit(): void {
    const pageInfo = { pageIndex: 0, pageSize: 10};
    this.loadBooks(pageInfo);
    this.publishingHouseService.bookToDelete.subscribe((book) =>{
      this.publishingHouseService.deleteBook(book.id).subscribe(() => {
        this.loadBooks({ pageIndex: this.pageIndex, pageSize: this.pageSize});
      })
    })
  }

  public loadBooks(event: any) {
    this.pageable = new Pageable(event.pageIndex, this.pageSize);
    this.bookService.getBooks(this.pageable).subscribe(data => {
        this.books = data.content;
        this.numberOfItems = data.totalElements;
        this.pageSize = data.pageable.pageSize;
        this.pageIndex = data.pageable.pageIndex;
      }
    )
  }
}
