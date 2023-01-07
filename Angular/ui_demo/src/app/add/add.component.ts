import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../sevices/user.service';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit{
  public user: any = {
    firstName: '',
    lastName: '',
    email: ''
  };

  constructor(private userService: UserService, private router: Router) {}
  ngOnInit(): void {
  }

  addUser() {
    this.userService.addUser(this.user).subscribe(data => {
      alert("Thêm mới người dùng thành công")
      this.router.navigateByUrl('/');
    });
  }

}
