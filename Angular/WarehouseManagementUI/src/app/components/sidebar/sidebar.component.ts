import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit{
  public isLoggedIn$: Observable<boolean> = new Observable<boolean>();

  ngOnInit(): void {
    this.isLoggedIn$ = this.auth.isLoggedIn();
  }

  constructor(private auth: AuthService, private router: Router){}

  logout() {
    this.auth.logout()
    this.auth.loggedIn.next(false);
    this.router.navigateByUrl('')
  }

}
