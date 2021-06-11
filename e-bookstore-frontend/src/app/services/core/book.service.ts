import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import { BooksApi } from "../../configuration/api/books-api";
import {IPage, IPageable} from "../../shared/util/request.utils";
import {HttpClient} from "@angular/common/http";
import {CreateBook} from "../../shared/model/book.model";

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private httpClient: HttpClient) {
  }

  public getBooks(pageable: IPageable): Observable<any>{
    const url = BooksApi.public.findAll + '?page=' + pageable.page + '&size=' + pageable.size;
    return this.httpClient.get(url);
  }

  public createBook(book: CreateBook): Observable<any> {
    return this.httpClient.post(BooksApi.private.create, book);
  }

  public searchBooks(book: CreateBook, pageable: IPageable): Observable<any> {
    let url: string = "";
    if (book.categoryId && book.title) {
      url = "&title=" + book.title + "&categoryId=" + book.categoryId;
    } else if (book.categoryId && !book.title) {
      url = "&categoryId=" + book.categoryId;
    } else if (!book.categoryId && book.title) {
      url = "&title=" + book.title;
    }
    url = BooksApi.public.findByTitleAndCategory  + '?page=' + pageable.page + '&size=' + pageable.size + url;
    return this.httpClient.get(url);
  }

}
