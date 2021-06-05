import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import { AuthUser, CreateRepresentativeUser, CreateUser, IUser } from "../../shared/model/user.model";
import {map} from "rxjs/operators";


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly BASE_URL = 'spring/api/public/auth';

  private readonly API_REGISTER_USER = '/register/user';
  private readonly API_REGISTER_REPRESENTATIVE = '/register/representative';
  private readonly API_LOGIN = '/login';

  private readonly LOCAL_STORAGE_USER: string = 'currentUser';
  private readonly LOCAL_STORAGE_USER_TOKEN: string = 'userToken';

  private currentUserSubject!: BehaviorSubject<any>;
  private currentUser!: Observable<any>;

  constructor(private httpClient: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<any>(JSON.parse(localStorage.getItem(this.LOCAL_STORAGE_USER) || '{}'));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public isAuthenticated(): boolean {
    return !!localStorage.getItem(this.LOCAL_STORAGE_USER);
  }

  public getCurrentUserValue(): any {
    return this.currentUserSubject.value;
  }

  public getUserToken(): any {
    if(localStorage.getItem(this.LOCAL_STORAGE_USER_TOKEN)) {
      return JSON.parse(<string>localStorage.getItem(this.LOCAL_STORAGE_USER_TOKEN)).token;
    }
    return null;
  }

  public register(createUser: CreateUser): Observable<IUser> {
    const url = this.BASE_URL + this.API_REGISTER_USER;
    return this.httpClient.post<IUser>(url, createUser);
  }

  public registerRepresentative(createRepresentativeUser: CreateRepresentativeUser): Observable<IUser>{
    const url = this.BASE_URL + this.API_REGISTER_REPRESENTATIVE;
    return this.httpClient.post<IUser>(url, createRepresentativeUser);
  }

  public login(authUser: AuthUser): Observable<IUser> {
    const url = this.BASE_URL + this.API_LOGIN;

    return this.httpClient.post<IUser>(url, authUser, {observe: 'response'}).pipe(
      map(response => {
        localStorage.setItem(this.LOCAL_STORAGE_USER, JSON.stringify(response.body));
        localStorage.setItem(this.LOCAL_STORAGE_USER_TOKEN, JSON.stringify({token: response.headers.get('authorization')}));
        this.currentUserSubject.next(response.body);
        return <IUser>response.body;
      })
    )
  }

  public logout(): void {
    localStorage.removeItem(this.LOCAL_STORAGE_USER);
    localStorage.removeItem(this.LOCAL_STORAGE_USER_TOKEN);
    this.currentUserSubject.next(null);
  }

}
