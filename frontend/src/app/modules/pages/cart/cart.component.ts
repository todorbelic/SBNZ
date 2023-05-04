import { Component, OnInit } from '@angular/core';
import { CartService } from "../../bookstore/services/cart.service";
import { FormsModule } from '@angular/forms';

import "bootstrap"
import { Address } from '../../bookstore/dto/adress.model';
import { UserCart } from '../../bookstore/dto/user-cart.model';
import { TokenStorageService } from '../../bookstore/services/token-storage.service';
import { ProcessedOrder } from '../../bookstore/dto/processed-order';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cartItems: any[] = [];
  address: Address;
  isCard: boolean = false;
  orderProcessed!: any;

  constructor(public cartService: CartService, private tokenStorageService: TokenStorageService) {
    this.address = new Address();
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

  checkout(): void {
    alert('Checkout successful');
    const user = this.tokenStorageService.getUser();
    const userCart = new UserCart(Number(user.id), this.orderProcessed, this.address, 0);

    this.cartService.checkout(userCart)
    this.clearCart();
  }

  getRounded(disc: number): any {
    return (disc).toFixed(2);
  }
}
