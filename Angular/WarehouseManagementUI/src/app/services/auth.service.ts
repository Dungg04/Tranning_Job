import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(
    false
  );


  constructor(private http: HttpClient) { }

  login(data: any): Observable<any> {
    return this.http.post("http://localhost:8080/v1/api/auth/login", data);
  }

  getAuthorizationToken(): string {
    const token = window.localStorage.getItem('token');
    if (token!=null) {
      return token;
    }
    return '';
  }

  logout(): any{
    const headers = new HttpHeaders().set('Authorization','')
    return headers;
  }

  public isLoggedIn(): Observable<boolean> {
    return this.loggedIn.asObservable();
  }
}
