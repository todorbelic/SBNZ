import { Component, OnInit } from '@angular/core';
import { CartService } from "../../bookstore/services/cart.service";

import "bootstrap"

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cartItems: any[] = [];


  constructor(public cartService: CartService) { }

  ngOnInit(): void {
    this.getCartItems();
  }

  getCartItems(): void {
    this.cartItems = JSON.parse(localStorage.getItem('cartItems') ?? '[]');
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

  checkout(): void {
    alert('Checkout successful');
    this.cartService.checkout(this.cartItems);
    this.clearCart();
  }

  getTotal(): number {
    const total = this.cartService.calculateTotalWithDiscount(this.cartItems);

    return Math.round(total * 100) / 100;;
  }
}
