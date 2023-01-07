import { Component, OnInit } from '@angular/core';
import { UserService } from '../sevices/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit{
  users: any[]=[];
  constructor(private user: UserService) {

  }
  ngOnInit(): void {
    this.user.getUsers().subscribe(res => {
      this.users = res;
    })
  }

  deleteUser(id: number) {
    this.user.deleteUser(id).subscribe(data => {
      alert("Xoá thành công")
      this.users = this.users.filter((item: any) => {
        return item.id != id;
      })
    })
  }
}
