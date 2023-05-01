import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { HomeComponent } from './home/home.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { SignInComponent } from './sign-in/sign-in.component';
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    HomeComponent,
    SignUpComponent,
    SignInComponent,
  ],
    imports: [
        CommonModule,
        AppRoutingModule,
        ReactiveFormsModule,
    ]
})
export class PagesModule { }
