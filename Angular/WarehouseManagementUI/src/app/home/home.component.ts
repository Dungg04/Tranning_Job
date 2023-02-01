import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  productName = '';
  p: number = 1;
  products: any[]=[];
  startP = 0;
  endP = 0;
  constructor(
    private product: ProductService,
    ){
      for(let i=0; i<this.products.length; i++) {
        this.products.push(`item ${i}`);
      }
    }

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
    }, error => {
        alert("Không tìm được sản phẩm nào")
        this.productName = ""
    }
    )
  }

  serachByPrice(){
    this.product.getProductByPriice(this.startP, this.endP).subscribe(res => {
      this.products = res;
    }, error => {
        alert("Không tìm được sản phẩm nào")
        this.startP = 0;
        this.endP = 0;
    }
    )
  }
}
