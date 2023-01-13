import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  productName = '';
  products: any[]=[];
  constructor(private product: ProductService){}

  ngOnInit(): void {
    this.product.getProduct().subscribe(res => {
      this.products = res;
    })
  }

  sortByAmount() {
    this.product.sortByAmount().subscribe(res => {
      this.products = res;
    })
  }

  searchByName(){
    this.product.getProductsByName(this.productName).subscribe(res => {
      this.products = res;
      console.log(res);
    })
    .unsubscribe()
  }
}
