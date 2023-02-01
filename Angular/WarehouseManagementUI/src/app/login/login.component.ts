import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  public account: any = {
    username: '',
    password: ''
  }

  ngOnInit(): void {
  }

  constructor(private service: AuthService, private roter: Router){}

  login() {
    this.service.login(this.account).subscribe(data => {
      console.log(data.result.jwt)
      window.localStorage.setItem("token",data.result.jwt)
      this.roter.navigate(['/home'])
      this.service.loggedIn.next(true)
    }
    , error => {
      alert("Tên đăng nhập hoặc mật khẩu không đúng")
    }
    )


  }
}
