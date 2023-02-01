import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { CustomerService } from '../services/customer.service';

@Component({
  selector: 'app-edit-customer',
  templateUrl: './edit-customer.component.html',
  styleUrls: ['./edit-customer.component.css']
})
export class EditCustomerComponent implements OnInit {
  public customer: any = {
    custommerID: '',
    customerName: '',
    address: '',
    phoneNumber: ''
  };

  constructor(private service: CustomerService, private roter: Router, private route: ActivatedRoute){}

  ngOnInit(): void {
    this.getDetailCustomer();
  }

  getDetailCustomer() {
    this.route.params.subscribe(data => {
      this.service.getDetailCustomer(data['customerID']).subscribe(data => {
        this.customer = data;
      // console.log(data)
      })
    })
  }

  editCustomer() {
    this.service.editCustomer(this.customer.customerID, this.customer).subscribe(data => {
      alert("Sửa thành công")
      this.roter.navigateByUrl("/getCustomer")
    })
  }

  deleteCusomer() {
    this.service.deleteCustomer(this.customer.customerID).subscribe(data => {
      alert("Xoá thành công")
      this.roter.navigateByUrl("/getCustomer")
    })
  }


}
