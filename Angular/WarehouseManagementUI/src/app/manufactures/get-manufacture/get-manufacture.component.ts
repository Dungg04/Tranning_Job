import { Component, OnInit } from '@angular/core';
import { ManufactureService } from '../services/manufacture.service';

@Component({
  selector: 'app-get-manufacture',
  templateUrl: './get-manufacture.component.html',
  styleUrls: ['./get-manufacture.component.css']
})
export class GetManufactureComponent implements OnInit {
  p: number = 1;
  manufactures: any[] = [];
  constructor(private service: ManufactureService) {
    for(let i=0; i<this.manufactures.length; i++) {
      this.manufactures.push(`item${i}`);
    }
  }

  ngOnInit(): void {
    this.service.getManufacture().subscribe(res => {
      this.manufactures = res;
    })
  }

}
