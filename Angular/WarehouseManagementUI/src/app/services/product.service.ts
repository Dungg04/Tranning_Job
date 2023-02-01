import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http'
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient, private authService: AuthService) {
    this.getProduct();
  }

  public setheader():any{
    let token = `Bearer ${this.authService.getAuthorizationToken()}`
    const headers = new HttpHeaders().set('Authorization',token)
    return headers
   }


  API_URL = "http://localhost:8080/v1/api/products";
  API_URL_FIND = "http://localhost:8080/v1/api/products/find";
  getProduct(): Observable<any> {
    const headers = this.setheader()
    return this.http.get<any>(`${this.API_URL}`, {
      headers,
      responseType: 'json'
    });
  }

  getProductsByName(productName: string): Observable<any> {
    const headers = this.setheader()
    return this.http.get<any>(`${this.API_URL}/getByName/${productName}`, {
      headers,
      responseType: 'json'
    });
  }

  getProductsById(productID: string): Observable<any> {
    const headers = this.setheader()
    return this.http.get<any>(`${this.API_URL}/getById/${productID}`, {
      headers,
      responseType: 'json'
    });
  }

  sortByAmount(): Observable<any> {
    const headers = this.setheader()
    return this.http.get<any>(`${this.API_URL}/sortByAmount`, {
      headers,
      responseType: 'json'
    });
  }

  addProduct(productID: string, data: any) {
    const headers = this.setheader()
    return this.http.post(`${this.API_URL}/${productID}`, data, {
      headers,
      responseType: 'json'
    });
  }

  getDetailProduct(productID: string) {
    const headers = this.setheader()
    return this.http.get<any>(`${this.API_URL}/${productID}`, {
      headers,
      responseType: 'json'
    });
  }

  editProduct(productID: string, data: any){
    const headers = this.setheader()
    return this.http.put(`${this.API_URL}/${productID}`, data, {
      headers,
      responseType: 'json'
    });
  }

  getProductByPriice(startP: number, endP: number) {
    const headers = this.setheader()
    return this.http.get<any>(`${this.API_URL_FIND}/${startP}/${endP}`, {
      headers,
      responseType: "json"
    });
  }




}
