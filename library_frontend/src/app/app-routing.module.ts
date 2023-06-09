import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { HomeComponent } from "./modules/pages/home/home.component";
import { SignUpComponent } from "./modules/pages/sign-up/sign-up.component";
import { SignInComponent } from "./modules/pages/sign-in/sign-in.component";
import { BooksComponent } from "./modules/pages/books/books.component";
import { CartComponent } from "./modules/pages/cart/cart.component";
import { AddBookComponent } from "./modules/pages/add-book/add-book.component";

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'sign-up', component: SignUpComponent },
  { path: 'sign-in', component: SignInComponent },
  { path: 'books', component: BooksComponent },
  { path: 'cart', component: CartComponent },
  { path: 'admin/addBook', component: AddBookComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
