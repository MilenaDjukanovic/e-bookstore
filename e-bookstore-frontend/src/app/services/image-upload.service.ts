import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ImageUploadService {

  constructor(private httpClient: HttpClient) { }

  public uploadImage(image: File): Observable<any>{
    const url = environment.imgur.uploadUrl;

    const formData = new FormData();
    formData.append('image', image, image.name);

    const headers = new HttpHeaders({
      "Authorization": 'Client-ID ' + environment.imgur.clientId
    });

    return this.httpClient.post(url, formData, {headers: headers});
  }
}
