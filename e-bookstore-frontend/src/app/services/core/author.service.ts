import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {IPageable} from "../../shared/util/request.utils";
import {Observable} from "rxjs";
import {ICreateAuthor} from "../../shared/model/author.model";

@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  private baseURL = 'spring';

  constructor(private httpClient: HttpClient) { }

  public getAuthors(pageable: IPageable): Observable<any> {
    const url = this.baseURL + 'api/public/authors?page=' + pageable.page + '&size=' + pageable.size;
    return this.httpClient.get(url);
  }

  public createAuthor(author: ICreateAuthor): Observable<any> {
    const url = this.baseURL + "api/public/authors";
    return this.httpClient.post(url, author);
  }
}
