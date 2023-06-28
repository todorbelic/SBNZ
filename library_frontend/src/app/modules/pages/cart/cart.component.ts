import { Component, OnInit } from '@angular/core';
import { CartService } from "../../bookstore/services/cart.service";
import { FormsModule } from '@angular/forms';

import "bootstrap"
import { Address } from '../../bookstore/dto/adress.model';
import { UserCart } from '../../bookstore/dto/user-cart.model';
import { TokenStorageService } from '../../bookstore/services/token-storage.service';
import { ProcessedOrder } from '../../bookstore/dto/processed-order';
import {Card} from "../../bookstore/dto/card.model";
import {UserCartCard} from "../../bookstore/dto/user-cart-with-card.model";
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cartItems: any[] = [];
  address: Address;
  card: Card;
  isCard: string = '';
  orderProcessed!: any;

  constructor(public cartService: CartService, private tokenStorageService: TokenStorageService, private http: HttpClient) {
    this.address = new Address();
    this.card = new Card();
  }

  ngOnInit(): void {
    this.getCartItems();
  }

  getCartItems(): void {
    var cartItemsFromStorage = JSON.parse(localStorage.getItem('cartItems') ?? '[]');
    this.cartService.calculateTotalWithDiscount(cartItemsFromStorage).subscribe((res: any) => {
        this.orderProcessed = res;
        this.cartItems = this.orderProcessed.orderItems;
    });
}

  removeFromCart(book: any): void {
    let cart = JSON.parse(localStorage.getItem('cartItems') ?? '[]');
    const existingItem = cart.find((item: any) => item.id === book.id);
    if (existingItem.quantity > 1) {
      existingItem.quantity--;
    } else {
      cart = cart.filter((item: any) => item.id !== book.id);
    }

    localStorage.setItem('cartItems', JSON.stringify(cart));
    this.getCartItems();
  }

  clearCart(): void {
    localStorage.removeItem('cartItems');
    this.getCartItems();
  }

  public checkout(): void {
    const user = this.tokenStorageService.getUser();
    if(this.isCard == 'cash') {
      alert('Checkout successful');
      const userCart = new UserCart(Number(user.id), this.orderProcessed, this.address, 0);
      this.cartService.checkout(userCart)
      this.clearCart();
    } else if (this.isCard == 'card') {
      this.card.number = "123123123123"
      this.card.expirationDate = "2025-01-01"
      this.card.cvc = "323"
      const userCardCart = new UserCartCard(Number(user.id), this.orderProcessed, this.address, this.card, 1);
      console.log(userCardCart)
      this.getIPAddress().subscribe((response: any) => {
        const ipAddress = response.ip;
        userCardCart.ipAddress = ipAddress;
        userCardCart.ipAddress = "192.158.1.38"
        console.log(userCardCart);
        this.cartService.checkoutCard(userCardCart);
        this.clearCart();
      });
    }

  }

  getRounded(disc: number): any {
    return (disc).toFixed(2);
  }
  getIPAddress() {
    return this.http.get('https://api.ipify.org/?format=json');
  }

}
