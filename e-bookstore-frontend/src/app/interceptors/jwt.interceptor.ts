import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from "../../environments/environment";
import { AuthService } from "../services/authority/auth.service";

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    // Only add token to private APIs
    if(request.url.indexOf(environment.bookstore.api.base + environment.bookstore.api.private.base) === -1){
      return next.handle(request)
    }

    const token = this.authService.getUserToken();
    if(token){
      request = request.clone({
        setHeaders: {
          Authorization: 'Bearer ' + token
        }
      });
    }
    return next.handle(request);
  }

  public test(): void{

  }
}
