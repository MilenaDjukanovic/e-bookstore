import { Injectable } from '@angular/core';
import { CategoryApi } from "../../configuration/api/category-api";
import {IPageable} from "../../shared/util/request.utils";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {ICreateCategory} from "../../shared/model/category.model";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private httpClient: HttpClient) { }

  public getCategories(pageable: IPageable): Observable<any> {
    const url = CategoryApi.public.findAll + '?page=' + pageable.page + '&size=' + pageable.size;
    return this.httpClient.get(url);
  }

  public createCategory(category: ICreateCategory): Observable<any> {
    return this.httpClient.post(CategoryApi.private.create, category);
  }
}
