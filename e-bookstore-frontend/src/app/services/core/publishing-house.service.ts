import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import {Observable, Subject} from "rxjs";
import { PublishingHouseApi } from "../../configuration/api/publishing-house-api";
import { CreatePublishingHouse, IPublishingHouse } from "../../shared/model/publishing-house.model";

@Injectable({
  providedIn: 'root'
})
export class PublishingHouseService {

  public bookToDelete: Subject<any> = new Subject<any>();

  constructor(private httpClient: HttpClient){}

  public create(createPublishingHouse: CreatePublishingHouse): Observable<IPublishingHouse>{
    return this.httpClient.post<IPublishingHouse>(PublishingHouseApi.public.create, createPublishingHouse);
  }

  public deleteBook(bookId: number):Observable<any>{
    const url = PublishingHouseApi.private.delete + bookId;
    return this.httpClient.delete(url);
  }

  public onDeleteBook(event: any): void {
    this.bookToDelete.next(event);
  }
}
