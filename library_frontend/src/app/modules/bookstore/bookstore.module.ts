import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BooksComponent } from './books/books.component';
import { BooksDirective } from './books.directive';



@NgModule({
  declarations: [
    BooksComponent,
    BooksDirective
  ],
  imports: [
    CommonModule
  ]
})
export class BookstoreModule { }
