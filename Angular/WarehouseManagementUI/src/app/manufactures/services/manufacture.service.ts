import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class ManufactureService {

  constructor(private http: HttpClient, private authService: AuthService) {
    this.getManufacture();
  }

  public setheader():any{
    let token = `Bearer ${this.authService.getAuthorizationToken()}`
    const headers = new HttpHeaders().set('Authorization',token)
    return headers
   }


  API_URL = "http://localhost:8080/v1/api/manufactures";
  getManufacture(): Observable<any> {
    const headers = this.setheader()
    return this.http.get<any>(`${this.API_URL}`, {
      headers,
      responseType: 'json'
    });
  }

  getManufactureById(manufactureID: string): Observable<any> {
    const headers = this.setheader()
    return this.http.get<any>(`${this.API_URL}/${manufactureID}`, {
      headers,
      responseType: 'json'
    });
  }

  addMenufacture(manufactureID: string, data: any) {
    const headers = this.setheader()
    return this.http.post(`${this.API_URL}/${manufactureID}`, data, {headers});
   }
}
