import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit{
  public isLoggedIn$: Observable<boolean> = new Observable<boolean>();

  constructor(private authService: AuthService) {}

  public ngOnInit(): void {
    this.isLoggedIn$ = this.authService.isLoggedIn();
  }
}
