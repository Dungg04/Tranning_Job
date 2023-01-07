import { Component, OnInit } from '@angular/core';
import { HttpServiceService } from '../Services/http-service.service';

@Component({
  selector: 'app-post-data',
  templateUrl: './post-data.component.html',
  styleUrls: ['./post-data.component.css']
})
export class PostDataComponent implements OnInit{
  constructor(private httpClient: HttpServiceService){}
  ngOnInit(): void {
    const payload = {body: 'comment 3', postId: 1};
    this.httpClient.PostComment(payload).subscribe((data) => {
      console.log('postComment', data);
    })
  }

}
