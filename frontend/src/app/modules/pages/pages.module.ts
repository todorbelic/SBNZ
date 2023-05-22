import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { HomeComponent } from './home/home.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { BooksComponent } from './books/books.component';
import { MaterialModule } from "../../material/material.module";
import { CartComponent } from './cart/cart.component';
import { AddBookComponent } from './add-book/add-book.component';
import { LeaveRatingComponent } from './leave-rating/leave-rating.component';


@NgModule({
  declarations: [
    HomeComponent,
    SignUpComponent,
    SignInComponent,
    BooksComponent,
    CartComponent,
    AddBookComponent,
    LeaveRatingComponent
  ],
  imports: [
    CommonModule,
    AppRoutingModule,
    ReactiveFormsModule,
    MaterialModule,
    FormsModule,
  ]
})
export class PagesModule { }
