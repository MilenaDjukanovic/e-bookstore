import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { PublishingHouseApi } from "../../configuration/api/publishing-house-api";
import { CreatePublishingHouse, IPublishingHouse } from "../../shared/model/publishing-house.model";

@Injectable({
  providedIn: 'root'
})
export class PublishingHouseService {

  constructor(private httpClient: HttpClient){}

  public create(createPublishingHouse: CreatePublishingHouse): Observable<IPublishingHouse>{
    return this.httpClient.post<IPublishingHouse>(PublishingHouseApi.public.create, createPublishingHouse);
  }
}
