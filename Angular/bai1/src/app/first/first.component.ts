import { Component, OnInit } from '@angular/core';
import { CommonService } from '../Services/common.service';

@Component({
  selector: 'app-first',
  templateUrl: './first.component.html',
  styleUrls: ['./first.component.css']
})
export class FirstComponent implements OnInit {
  public loginName = 'user'
  public counter = 3;
  public bp = 0;

  constructor(private common: CommonService) {}

  ngOnInit(): void {
    this.bp = this.common.binhPhuong(this.counter);
  }

  callPhone(value: string) {
    console.warn(`Calling ${value} ...`);
  }

  public birthday = new Date(2001, 11, 4);

  check = true;

  getFormat() {
    return this.check ? 'shortDate' : 'fullDate';
  }

  checkFormat() {
    this.check = !this.check;
  }

}

template: `
  <p>Birthday: {{birthday | date:format}}</p>
  <button type="button" (click)="checkFormat()">Toggle format</button>
`

