import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../services/customer.service';

@Component({
  selector: 'app-get-customer',
  templateUrl: './get-customer.component.html',
  styleUrls: ['./get-customer.component.css']
})
export class GetCustomerComponent implements OnInit{
  customerID = '';
  p: number = 1;
  customers: any[]=[];
  constructor(private customer: CustomerService){
    for(let i=0; i<this.customers.length; i++) {
      this.customers.push(`item ${i}`);
    }
  }

  ngOnInit(): void {
    this.customer.getCustomer().subscribe(res => {
      this.customers = res;
      console.log(res)
    })
  }

}
