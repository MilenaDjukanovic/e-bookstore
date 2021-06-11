import {Component, OnInit} from '@angular/core';
import {BookService} from "../../services/core/book.service";
import {Book, CreateBook} from "../../shared/model/book.model";
import {IPageable, Pageable} from "../../shared/util/request.utils";
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
  public fieldConfigs: FieldConfig[] = defaultBookSearch;
  private pageIndex!: number;
  private pageable!: Pageable;

  private searchFilterOn = false;
  private bookWithFilters!: CreateBook;

  constructor(private bookService: BookService, private publishingHouseService: PublishingHouseService) {
  }

  ngOnInit(): void {
    const pageInfo = {pageIndex: 0, pageSize: 1};
    this.loadBooks(pageInfo);
    this.publishingHouseService.bookToDelete.subscribe((book) => {
      this.publishingHouseService.deleteBook(book.id).subscribe(() => {
        this.loadBooks({pageIndex: this.pageIndex, pageSize: this.pageSize});
      })
    })
  }

  public loadBooks(event: any) {
    this.pageable = new Pageable(event.pageIndex, event.pageSize);
    if (this.searchFilterOn) {
      this.getBooksForFilter(this.bookWithFilters, this.pageable);
    } else {
      this.getAllBooks(this.pageable);
    }
  }

  public onSubmit($event: any) {
    this.bookWithFilters = new CreateBook($event.title, "",
      "", 0, 0, 0, $event.categoryId);
    this.pageable = new Pageable(0, 1);
    this.getBooksForFilter(this.bookWithFilters, this.pageable);
  }

  private getAllBooks(pageable: IPageable): void {
    this.bookService.getBooks(pageable).subscribe(data => {
        this.books = data.content;
        this.numberOfItems = data.totalElements;
        this.pageSize = data.pageable.pageSize;
        this.pageIndex = data.pageable.pageIndex;
      }
    )
  }

  private getBooksForFilter(createBook: CreateBook, pageable: IPageable): void {
    this.bookService.searchBooks(createBook, pageable).subscribe(data => {
      this.books = data.content;
      this.numberOfItems = data.totalElements;
      this.pageSize = data.pageable.pageSize;
      this.pageIndex = data.pageable.pageIndex;
    });
    this.searchFilterOn = true;
  }
}
