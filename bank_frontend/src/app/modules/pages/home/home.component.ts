import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../bookstore/services/token-storage.service";



@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  isLoggedIn: boolean = false;


  constructor(private tokenStorageService: TokenStorageService) {
    this.isLoggedIn = this.tokenStorageService.isLoggedIn()
  }

  ngOnInit(): void {
  }

}
