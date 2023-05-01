import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {AuthService} from "../../bookstore/services/auth.service";
import {Router} from "@angular/router";
import {RegisterRequest} from "../../bookstore/dto/register-request.model";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  registerForm = new FormGroup({
    firstName: new FormControl<string | undefined>(undefined),
    lastName: new FormControl<string | undefined>(undefined),
    email: new FormControl<string | undefined>(undefined),
    username: new FormControl<string | undefined>(undefined),
    password: new FormControl<string | undefined>(undefined)
  })

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  public signUp() {
    const registerRequest:RegisterRequest = new RegisterRequest({
      username: this.registerForm.controls.username.value!,
      firstName: this.registerForm.controls.firstName.value!,
      lastName: this.registerForm.controls.lastName.value!,
      password: this.registerForm.controls.password.value!,
    })
    console.log(registerRequest);
    this.authService.register(registerRequest).subscribe({
        next: response => {
          console.log(response)
          alert("Successfully registered, you can log in!");
          this.router.navigate(['']).then(
            ()=>{
              window.location.reload();
            }
          );
        },
        error: message => {
          console.log(message.Error)
          alert("Boom!Error!")
        }

      }
    )
  }

}
