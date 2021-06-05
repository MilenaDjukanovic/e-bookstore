import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { CreatePublishingHouse, IPublishingHouse } from "../../shared/model/publishing-house.model";

@Injectable({
  providedIn: 'root'
})
export class PublishingHouseService {
  // #TODO these should be in the environment file
  private readonly BASE_URL = '/spring/api/public/publishing-houses';

  private readonly API_CREATE_PUBLISHING_HOUSE = '/create';

  constructor(private httpClient: HttpClient){}

  public create(createPublishingHouse: CreatePublishingHouse): Observable<IPublishingHouse>{
    const url = this.BASE_URL + this.API_CREATE_PUBLISHING_HOUSE;
    return this.httpClient.post<IPublishingHouse>(url, createPublishingHouse);
  }
}
