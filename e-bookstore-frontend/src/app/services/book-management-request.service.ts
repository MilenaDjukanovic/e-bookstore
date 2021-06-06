import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {IPageable} from "../shared/util/request.utils";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BookManagementRequestService {

  private baseURL = 'spring';

  constructor(private httpClient: HttpClient) {
  }

  public getBookManagementRequestsByProcessedAndByPublishingHouse(
    pageable: IPageable, id: number, processed: boolean): Observable<any> {
    const url = this.baseURL + "/api/public/book-management-requests?page=" +
      pageable.page + '&size=' + pageable.size + '&id=' + id + '&processed=' + processed;

    return this.httpClient.get(url);
  }

  public getAllPendingBookManagementRequests(processed: boolean, pageable: IPageable): Observable<any> {
    const url = this.baseURL + "/api/public/book-management-requests/pending?page=" +
      pageable.page + '&size=' + pageable.size + '&processed=' + processed;;
    return this.httpClient.get(url);
  }
}
