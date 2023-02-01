import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  constructor(private http: HttpClient, private authService: AuthService) {
    this.getReportReceipt();
  }

  public setheader():any{
    let token = `Bearer ${this.authService.getAuthorizationToken()}`
    const headers = new HttpHeaders().set('Authorization',token)
    return headers
   }

  API_URL_Receipt = "http://localhost:8080/v1/api/receipts";
  API_URP_RR = "http://localhost:8080/v1/api/receipts/report";
  API_URL_D = "http://localhost:8080/v1/api/receipts/reportReceipt"

  getReportReceipt(): Observable<any>{
    const headers = this.setheader()
    return this.http.get<any>(`${this.API_URL_Receipt}`, {headers, responseType: 'json'});
  }

  getReport(receiptID: number): Observable<any> {
    const headers = this.setheader()
    return this.http.get<any>(`${this.API_URP_RR}/${receiptID}`, {
      headers,
      observe: 'response',
      responseType: 'blob' as 'json',
    });

  }

  getReportReceiptDetail(receiptID: number): Observable<any> {
    const headers = this.setheader()
    return this.http.get<any>(`${this.API_URL_D}/${receiptID}`, {
      headers,
      responseType: 'json'});
  }

}
