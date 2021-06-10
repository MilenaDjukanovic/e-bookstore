import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from "rxjs/operators";
import { environment } from "../../environments/environment";
import { AuthService } from "../services/authority/auth.service";

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(request).pipe(catchError(error => {
      if(error['status'] === 401){
        this.authService.logout();
        location.reload();
      }

      let errorMessage = (error['error'] && error['error']['message']) ? error['error']['message'] : error['statusText'];
      // Handle specific imgur errors, their structure is different
      if(request.url.indexOf(environment.imgur.uploadUrl) !== -1){
        if(error.error.data.error.message){
          errorMessage = error.error.data.error.message;
        }
      }

      return throwError(errorMessage);
    }));
  }
}
