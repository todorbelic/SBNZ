import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BookWithAuthorsModel } from '../dto/book-with-authors.model';
import { TokenStorageService } from './token-storage.service';
import { UserCart } from '../dto/user-cart.model';
import { Order } from '../dto/order';
import { ProcessedOrder } from '../dto/processed-order';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private orderBaseUrl = 'http://localhost:8082/orders';
  private purchaseBaseUrl = 'http://localhost:8082/purchases'
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });


  constructor(private http: HttpClient, private tokenStorageService: TokenStorageService) { }

  calculateTotalWithDiscount(cartItems: BookWithAuthorsModel[]) :  Observable<any>{
    var order = new Order();
    order.orderItems = cartItems;
    return this.http.post(this.orderBaseUrl + '/getProcessedOrder' , order, { headers: this.headers });
 }

  checkout(userCart: UserCart) {
    this.http.post(this.purchaseBaseUrl + '/createPurchase', userCart, { headers: this.headers }).subscribe(res => {
      console.log(res);
    });
  }
}
