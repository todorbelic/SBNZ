import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BookWithAuthorsModel } from '../dto/book-with-authors.model';
import { TokenStorageService } from './token-storage.service';
import { UserCart } from '../dto/user-cart.model';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private baseUrl = 'http://localhost:8082/api/v1/cart';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });


  constructor(private http: HttpClient, private tokenStorageService: TokenStorageService) { }

  calculateTotalWithDiscount(cartItems: BookWithAuthorsModel[]) {
    this.http.post(this.baseUrl + '/total-price-with-discount', cartItems, { headers: this.headers }).subscribe(res => {
      return res;
    });
  }

  checkout(userCart: UserCart) {
    this.http.post(this.baseUrl + '/checkout', userCart, { headers: this.headers }).subscribe(res => {
      console.log(res);
    });
  }
}
