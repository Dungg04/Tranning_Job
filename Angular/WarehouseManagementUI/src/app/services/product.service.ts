import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) {
    this.getProduct();
  }

  API_URL = "http://localhost:8080/v1/api/products";
  getProduct(): Observable<any> {
    return this.http.get<any>(`${this.API_URL}`);
  }

  getProductsByName(productName: string): Observable<any> {
    return this.http.get<any>(`${this.API_URL}/getByName/${productName}`);
  }

  getProductsById(productID: string): Observable<any> {
    return this.http.get<any>(`${this.API_URL}/getById/${productID}`);
  }

  sortByAmount(): Observable<any> {
    return this.http.get<any>(`${this.API_URL}/sortByAmount`);
  }

  addProduct(productID: string, data: any) {
    return this.http.post(`${this.API_URL}/${productID}`, data,{headers: {'Access-Control-Allow-Origin': '*'}});
  }

  getDetailProduct(productID: string) {
    return this.http.get<any>(`${this.API_URL}/${productID}`);
  }

  editProduct(productID: string, data: any){
    return this.http.put(`${this.API_URL}/${productID}`, data);
  }
}
