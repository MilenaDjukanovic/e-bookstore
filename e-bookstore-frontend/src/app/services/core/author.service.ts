import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { environment } from "../../../environments/environment";
import { AuthorsApi } from "../../configuration/api/authors-api";
import {IPageable} from "../../shared/util/request.utils";
import {Observable} from "rxjs";
import {ICreateAuthor} from "../../shared/model/author.model";

@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  constructor(private httpClient: HttpClient) { }

  public createAuthor(author: ICreateAuthor): Observable<any> {
    return this.httpClient.post(AuthorsApi.private.create, author);
  }
}
