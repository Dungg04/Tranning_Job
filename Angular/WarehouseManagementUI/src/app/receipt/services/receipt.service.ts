import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class ReceiptService {

  constructor(private http: HttpClient, private auth: AuthService) {

   }

   public setheader():any{
    let token = `Bearer ${this.auth.getAuthorizationToken()}`
    const headers = new HttpHeaders().set('Authorization',token)
    return headers
   }

   API_URL = "http://localhost:8080/v1/api/receipts";
   API_URL1 = "http://localhost:8080/v1/api/receiptDetails";

   addReceipt(manufactureID: string, data: any): Observable<any> {
    const headers = this.setheader()
    return this.http.post(`${this.API_URL}/${manufactureID}`, data, {
      headers,
      responseType: 'json'
    });
   }

   addReceiptDetail(productID: string, data: any): Observable<any> {
    const headers = this.setheader()
    return this.http.post(`${this.API_URL1}/${productID}`, data, {
      headers,
      responseType: 'json'
    });
   }

   getReceiptDetail(temp: number): Observable<any> {
    const headers = this.setheader()
    return this.http.get(`${this.API_URL}/byReceipt/${temp}`, {
      headers,
      responseType: 'json'
    });
   }

   edit(temp: number): Observable<any> {
    const headers = this.setheader();
    return this.http.get(`${this.API_URL1}/edit/${temp}`, {
      headers,
      responseType: 'json'
    });
   }

}
