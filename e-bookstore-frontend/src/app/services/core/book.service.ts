import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {IPage, IPageable} from "../../shared/util/request.utils";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private baseURL = 'spring';

  constructor(private httpClient: HttpClient) {
  }


  public getBooks(pageable: IPageable): Observable<any>{
    const url = this.baseURL + "/api/public/books?page=" + pageable.page + '&size=' + pageable.size;
    return this.httpClient.get(url);
  }
}
