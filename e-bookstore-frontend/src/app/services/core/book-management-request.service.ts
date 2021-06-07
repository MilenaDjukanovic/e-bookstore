import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
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
      pageable.page + '&size=' + pageable.size + '&processed=' + processed;

    return this.httpClient.get(url);
  }

  public approveBookManagementRequest(requestId: number): Observable<any> {
    const url = this.baseURL + "/api/public/book-management-requests/approve/" + requestId;
    return this.httpClient.put(url, {});
  }

  public rejectBookManagementRequest(bookManagementRequestId: number): Observable<any> {
    const url = this.baseURL + "/api/public/book-management-requests/delete/" + bookManagementRequestId;
    return this.httpClient.delete(url);
  }

  public createBookRequestService(bookRequest: CreateBookManagementRequest): Observable<any> {
    const url = this.baseURL + "/api/public/book-management-requests/create";
    return this.httpClient.post(url, bookRequest);
  }
}
