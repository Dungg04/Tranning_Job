import { Component, OnInit } from '@angular/core';
import { HttpServiceService } from '../Services/http-service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  constructor(private httpService: HttpServiceService){}

  ngOnInit(): void {
    // this.httpService.getComments().subscribe((data) => {
    //   console.log('data', data);
    // });
  }

}
