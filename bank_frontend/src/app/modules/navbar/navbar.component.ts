import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { UserToken } from "../bookstore/model/user-token.model";
import { TokenStorageService } from "../bookstore/services/token-storage.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  user: UserToken;
  role: any;
  isLoggedIn: boolean = false;
  isBanker: boolean = false;

  constructor(private tokenStorageService: TokenStorageService, private router: Router) {
    this.user = this.tokenStorageService.getUser()
    this.isLoggedIn = this.tokenStorageService.isLoggedIn()
    console.log(this.user.role)
    this.isBanker = this.user.role == 'BANK_OFFICER';
  }

  ngOnInit(): void {
  }

  signOut() {
    this.tokenStorageService.signOut()
    this.router.navigate(['']).then(() => {
      window.location.reload();
    })
  }

  routeToSignUp(): void {
    this.router.navigate(['/sign-up']);
  }

  routeToSignIn(): void {
    this.router.navigate(['/sign-in']);
  }


}
