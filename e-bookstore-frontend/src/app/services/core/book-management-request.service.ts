import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { BooksManagementRequestsApi } from "../../configuration/api/books-management-requests-api";
import {IPageable} from "../../shared/util/request.utils";
import {Observable} from "rxjs";
import {
  CreateBookManagementRequest,
  UpdateBookManagementRequest
} from "../../shared/model/book-management-requests.model";

@Injectable({
  providedIn: 'root'
})
export class BookManagementRequestService {

  constructor(private httpClient: HttpClient) {
  }

  public getBookManagementRequestsByProcessedAndByPublishingHouse(
    pageable: IPageable, id: number, processed: boolean): Observable<any> {
    const url = BooksManagementRequestsApi.public.findAll + '?page=' +
      pageable.page + '&size=' + pageable.size + '&id=' + id + '&processed=' + processed;

    return this.httpClient.get(url);
  }

  public getAllPendingBookManagementRequests(processed: boolean, pageable: IPageable): Observable<any> {
    const url = BooksManagementRequestsApi.private.findAllPending + '?page=' +
      pageable.page + '&size=' + pageable.size + '&processed=' + processed;

    return this.httpClient.get(url);
  }

  public approveBookManagementRequest(requestId: number): Observable<any> {
    const url = BooksManagementRequestsApi.private.approve + requestId;
    return this.httpClient.put(url, {});
  }

  public rejectBookManagementRequest(bookManagementRequestId: number): Observable<any> {
    const url = BooksManagementRequestsApi.private.delete + bookManagementRequestId;
    return this.httpClient.delete(url);
  }

  public createBookRequestService(bookRequest: CreateBookManagementRequest): Observable<any> {
    return this.httpClient.post(BooksManagementRequestsApi.private.create, bookRequest);
  }
}
