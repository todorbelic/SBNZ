import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { HomeComponent } from './home/home.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MaterialModule } from "../../material/material.module";
import { CreateCreditRequestComponent } from './create-credit-request/create-credit-request.component';
import { PackageComponent } from './package/package.component';


@NgModule({
  declarations: [
    HomeComponent,
    SignUpComponent,
    SignInComponent,
    CreateCreditRequestComponent,
    PackageComponent
  ],
  imports: [
    CommonModule,
    AppRoutingModule,
    ReactiveFormsModule,
    MaterialModule,
    FormsModule
  ]
})
export class PagesModule { }
