import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit{
  public product: any = {
    productID: '',
    productName: '',
    amount: '',
    price: ''
  };

  constructor(private service: ProductService, private roter: Router){}

  ngOnInit(): void {
  }

  addProduct() {
    this.service.addProduct(this.product.productID,this.product).subscribe(data => {
      alert("Thêm mới thành công")
      this.roter.navigateByUrl('/');
    })
  }

}
