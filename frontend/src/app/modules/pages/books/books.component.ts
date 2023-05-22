import { Component, OnInit } from '@angular/core';
import { BookService } from "../../bookstore/services/book.service";
import { BookWithAuthorsModel } from "../../bookstore/dto/book-with-authors.model";
import { TokenStorageService } from '../../bookstore/services/token-storage.service';
import {MatDialog} from "@angular/material/dialog";
import {LeaveRatingComponent} from "../leave-rating/leave-rating.component";

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {

  books: BookWithAuthorsModel[] = [];
  isLoggedIn: boolean = false;



  constructor(public dialog: MatDialog, public bookService: BookService, private tokenStorageService: TokenStorageService) {
    this.isLoggedIn = this.tokenStorageService.isLoggedIn()
  }

  ngOnInit(): void {
    this.getBooks();
  }

  getBooks(): void {
    this.bookService.getAll().subscribe(res => {
      this.books = res;
      console.log(res);
      console.log(this.books);
    });
  }

  addToCart(book: BookWithAuthorsModel): void {
    const cartItems: BookWithAuthorsModel[] = JSON.parse(localStorage.getItem('cartItems') || '[]');
    // add item to cart or increase quantity
    const existingItem = cartItems.find((item: BookWithAuthorsModel) => item.id === book.id);
    if (existingItem) {
      existingItem.quantity++;
    } else {
      book.quantity = 1;
      cartItems.push(book);
    }
    // cartItems.push(book);
    localStorage.setItem('cartItems', JSON.stringify(cartItems));
    alert(`${book.name} added to cart`);
  }

  gradeBook(book : BookWithAuthorsModel) : void {
    const dialogRef = this.dialog.open(LeaveRatingComponent, {
      width: '280px',
      data: {
        bookId: book.id,
        userId: this.tokenStorageService.getUser().id
      }
    })
  }

}
