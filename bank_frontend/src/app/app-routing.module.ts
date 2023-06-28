import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { HomeComponent } from "./modules/pages/home/home.component";
import { SignUpComponent } from "./modules/pages/sign-up/sign-up.component";
import { SignInComponent } from "./modules/pages/sign-in/sign-in.component";
import { CreateCreditRequestComponent } from "./modules/pages/create-credit-request/create-credit-request.component";


const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'sign-up', component: SignUpComponent },
  { path: 'sign-in', component: SignInComponent },
  { path: 'credit-request', component: CreateCreditRequestComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
