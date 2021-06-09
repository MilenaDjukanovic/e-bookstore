import { Injectable } from '@angular/core';
import {IPageable} from "../../shared/util/request.utils";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {ICreateCategory} from "../../shared/model/category.model";

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

  public createCategory(category: ICreateCategory): Observable<any> {
    const url = this.baseURL + 'api/public/categories';
    return this.httpClient.post(url, category);
  }
}
