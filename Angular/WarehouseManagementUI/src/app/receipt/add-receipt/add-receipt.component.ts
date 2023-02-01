import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ProductService } from 'src/app/services/product.service';
import { ManufactureService } from '../../manufactures/services/manufacture.service';
import { ReceiptService } from '../services/receipt.service';

@Component({
  selector: 'app-add-receipt',
  templateUrl: './add-receipt.component.html',
  styleUrls: ['./add-receipt.component.css']
})
export class AddReceiptComponent implements OnInit {
  public receiptDetail: any = {
    amount: 0,
    price: 0
  };

  @ViewChild('typeProductId')
  typeProductInput!: ElementRef;

  @ViewChild('typeManufactureId') typeManufactureInput!: ElementRef;

  public productID: any

  temp: any

  receiptsDetail: any[] = [];
  products: any[] = [];
  manufactures: any[] = [];
  receipts: any[] = [];

  constructor(private manufacture: ManufactureService, private receiptService: ReceiptService, private product: ProductService, private router: Router) { }

  ngOnInit(): void {
    this.getManufacture()
    this.getProduct()
  }

  getManufacture() {
    return this.manufacture.getManufacture().subscribe(res => {
      this.manufactures = res;
    })
  }

  getProduct() {
    return this.product.getProduct().subscribe(data => {
      this.products = data
    })
  }

  addReceiptDetail() {
    return this.receiptService.addReceiptDetail(this.typeProductInput?.nativeElement.value, this.receiptDetail).subscribe(data => {
      this.receiptsDetail[this.receiptsDetail.length] = data
      this.temp = data.temp
      console.log(this.temp)
      // console.log(data)
    })
  }

  getReceiptDetail() {
    return this.receiptService.getReceiptDetail(this.temp).subscribe(data => {
      this.receipts = data
      // console.log(data)
    })
  }


  addReceipt() {
    console.log(this.receiptsDetail)
    return this.receiptService.addReceipt(this.typeManufactureInput?.nativeElement.value, this.receipts).subscribe(data => {
      this.router.navigateByUrl(`/confirmReceipt/${this.temp}`)
      alert("Tạo phiếu nhập kho thành công!")
      // console.log(data)
    })
  }


}
