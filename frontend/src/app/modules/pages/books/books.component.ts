import { Component, OnInit } from '@angular/core';
import {BookService} from "../../bookstore/services/book.service";
import {BookWithAuthorsModel} from "../../bookstore/dto/book-with-authors.model";

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {

  books: BookWithAuthorsModel[] = [];

  constructor(public bookService: BookService) { }

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

}
