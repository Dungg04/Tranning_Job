import { Component, OnInit } from '@angular/core';
import { HttpServiceService } from '../Services/http-service.service';

@Component({
  selector: 'app-get-data',
  templateUrl: './get-data.component.html',
  styleUrls: ['./get-data.component.css']
})
export class GetDataComponent implements OnInit{
  constructor(private httpService: HttpServiceService) {};

  public Cmts: Comment[] = [];

  public ngOnInit(): void {
    this.getAllComment();
    this.httpService.getComments().subscribe((data) => {
      console.log('data', data);
    })
  }

  public getAllComment(): void {
    this.httpService.getComments().subscribe(cmt => this.Cmts = this.Cmts);

  }

}
