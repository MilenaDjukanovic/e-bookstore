import { Injectable } from '@angular/core';
import {IPageable} from "../../shared/util/request.utils";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private baseURL = 'spring';

  constructor(private httpClient: HttpClient) { }

  public getCategories(pageable: IPageable): Observable<any> {
    const url = this.baseURL + 'api/public/categories?page=' + pageable.page + '&size=' + pageable.size;
    return this.httpClient.get(url);
  }
}
