import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  constructor() { }

  public binhPhuong(n: number): number {
    return n*n;
  }

  public  submitData(data: any): void {
      console.log('gửi data lên service: ', data);
  }
}
