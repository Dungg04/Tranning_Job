import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from '../../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient,private authService: AuthService) {
    this.getCustomer();
   }

   public setheader():any{
    let token = `Bearer ${this.authService.getAuthorizationToken()}`
    const headers = new HttpHeaders().set('Authorization',token)
    return headers
   }

   API_URL = "http://localhost:8080/v1/api/customers";
   getCustomer(): Observable<any> {
    const headers = this.setheader()

    return this.http.get<any>(`${this.API_URL}`, {
      headers,
      responseType: 'json'
    });
   }

   getDetailCustomer(customerId: string): Observable<any> {
    const headers = this.setheader()

    return this.http.get<any> (`${this.API_URL}/${customerId}`, {
      headers,
      responseType: 'json'
    });
   }

   editCustomer(customerId: string, data: any): Observable<any> {
    const headers = this.setheader()

    return this.http.put(`${this.API_URL}/${customerId}`, data, {
      headers,
      responseType: 'json'
    });
   }

   addCustomer(customerID: string, data: any): Observable<any> {
    const headers = this.setheader()

    return this.http.post<any>(`${this.API_URL}/${customerID}`, data, {
      headers,
      responseType: 'json'
    })

   }

   deleteCustomer(customerID: string): Observable<any> {
    const headers = this.setheader()

    return this.http.delete(`${this.API_URL}/${customerID}`, {
      headers,
      responseType: 'text'
    });
   }
  //  addCustomer(customerID: string, data: any) {

  //   return this.http.post(`${this.API_URL}/${customerID}`, data);
  //  }
}
