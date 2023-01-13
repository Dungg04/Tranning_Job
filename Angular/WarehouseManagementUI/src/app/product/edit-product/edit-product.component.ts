import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit{
  public product: any = {
    productID: '',
    productName: '',
    amount: '',
    price: ''
  };

  constructor(private service: ProductService, private roter: Router, private route: ActivatedRoute){}

  ngOnInit(): void {
    this.getDetailProduct();
  }

  getDetailProduct(){
    this.route.params.subscribe(data => {
      this.service.getDetailProduct(data['productID']).subscribe(data => {
        this.product = data;
      })
    })
  }

  editProduct() {
    this.service.editProduct(this.product.productID, this.product).subscribe(data => {
      alert("Sửa thành công")
      this.roter.navigateByUrl("/")
    })
  }
}
