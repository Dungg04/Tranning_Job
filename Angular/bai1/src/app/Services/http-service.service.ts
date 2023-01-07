import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpServiceService {
  private REST_API_SERVER = 'https://jsonplaceholder.typicode.com';

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'Application/json'
    })
  }
  constructor(private http: HttpClient) { }

  public getComments(): Observable<any> {
    const url = `${this.REST_API_SERVER}/comments`;
    return this.http.get<any>(url, this.httpOptions);
  }

  public PostComment(payload: any): Observable<any> {
    const url = `${this.REST_API_SERVER}/comments`;
    return this.http.post<any>(url, payload, this.httpOptions);
  }
}
