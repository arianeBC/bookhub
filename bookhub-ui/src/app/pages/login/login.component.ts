import {Component} from '@angular/core';
import {AuthenticationRequest} from "../../services/models/authentication-request";
import {AuthenticationService} from "../../services/services/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  authRequest: AuthenticationRequest = {email: '', password: ''};
  errorMessage: Array<String> = [];
  showPassword = false;

  constructor(
    private router: Router,
    private authService: AuthenticationService
  ) {
  }

  login() {
    this.showPassword = false
    this.errorMessage = [];
    this.authService.authenticate({
      body: this.authRequest
    }).subscribe({
      next: () => {
        // TODO save token
        this.router.navigate(['books'])
      },
      error: (err) => {
        console.log(err);
        if (err.error.validationsErrors) {
          this.errorMessage = err.error.validationsErrors;
        } else {
          this.errorMessage.push(err.error.error);
        }
      }
    })
  }

  register() {
    this.router.navigate(['register']);
  }

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword; // Toggle the state
  }
}
