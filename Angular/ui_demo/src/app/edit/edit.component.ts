import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { UserService } from '../sevices/user.service';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit{
  public user: any = {
    firstName: '',
    lastName: '',
    email: ''
  };

  constructor(private userService: UserService, private route: ActivatedRoute, private Router: Router ) {}
  ngOnInit(): void {
    this.getDetailUser();
  }

  getDetailUser() {
    this.route.params.subscribe(data => {
      this.userService.getDetailUser(data['id']).subscribe(data => {
        this.user = data;
      })
    });
  }

  editUser() {
    this.userService.editUser(this.user.id  , this.user).subscribe(data => {
      alert("Sửa người dùng thành công")
      this.Router.navigateByUrl("/")
    });
  }
}
