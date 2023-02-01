import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { CustomerService } from '../services/customer.service';

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css']
})
export class AddCustomerComponent implements OnInit{
  public customer: any={
    customerID: '',
    customerName: '',
    address: '',
    phoneNumber: ''
  };

  constructor(private service: CustomerService, private router: Router) {}

  ngOnInit(): void {

  }

  @ViewChild('myForm') myForm: NgForm | undefined;

  addCustomer() {
    if(this.myForm?.valid) {
      this.service.addCustomer(this.customer.customerID, this.customer).subscribe(data => {
        alert("Thêm mới thành công")
        this.router.navigateByUrl('/getCustomer');
      }, error => {
        alert("Bạn không có quyền này")
      }
      )
    }
    else {
      alert("Form invalid!")
    }
  }

}
