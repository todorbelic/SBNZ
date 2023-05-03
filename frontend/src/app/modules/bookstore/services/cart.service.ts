import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BookWithAuthorsModel } from '../dto/book-with-authors.model';
import { TokenStorageService } from './token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private baseUrl = 'http://localhost:8082/api/v1/cart';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });


  constructor(private http: HttpClient, private tokenStorageService: TokenStorageService) { }

  calculateTotalWithDiscount(cartItems: BookWithAuthorsModel[]) {
    let total = 0;
    cartItems.forEach((item: any) => {
      total += item.price * item.quantity;
    });
    return total;

    // TODO: Integrate with backend
    // return this.http.get(this.baseUrl + '/total-price-with-discount', { headers: this.headers });
  }

  checkout(cartItems: BookWithAuthorsModel[]) {
    // send cartItems and user info to backend
    const user = this.tokenStorageService.getUser();

    this.http.post(this.baseUrl + '/checkout', { "cartItems": cartItems, "user": user }, { headers: this.headers }).subscribe(res => {
      console.log(res);
    });
  }
}
