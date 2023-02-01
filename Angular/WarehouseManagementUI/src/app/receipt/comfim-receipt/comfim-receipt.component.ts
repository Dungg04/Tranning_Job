import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ReceiptService } from '../services/receipt.service';

@Component({
  selector: 'app-comfim-receipt',
  templateUrl: './comfim-receipt.component.html',
  styleUrls: ['./comfim-receipt.component.css']
})
export class ComfimReceiptComponent implements OnInit{
  constructor(private service: ReceiptService, private roter: Router, private route: ActivatedRoute){}

  ngOnInit(): void {

  }

  edit() {
    this.route.params.subscribe( data => {
      this.service.edit(data['temp']).subscribe(res => {
          this.roter.navigateByUrl("/reportReceipt")
          // console.log(res)
        })
    })
  }
}
