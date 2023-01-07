import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient){
    this.getUsers();
  }

  API_URL = "http://localhost:8080/api/users";
  getUsers(): Observable<any> {
    return this.http.get<any>(`${this.API_URL}`);
  }

  addUser(data: any) {
    return this.http.post(this.API_URL, data);
  }

  editUser(id:number, data: any){
    return this.http.put(`${this.API_URL}/${id}`, data);
  }

  deleteUser(id: number) {
    return this.http.delete(`${this.API_URL}/${id}`);
  }

  getDetailUser(id: number) {
    return this.http.get(`${this.API_URL}/${id}`);
  }
}
