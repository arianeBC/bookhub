import {Component} from '@angular/core';
import {RegistrationRequest} from "../../services/models/registration-request";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/services/authentication.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {

  registrationRequest: RegistrationRequest = {email: '', firstName: '', lastName: '', password: ''};
  errorMessage: Array<string> = [];
  showPassword = false;

  constructor(
    private router: Router,
    private authService: AuthenticationService
  ) {
  }

  register() {
    this.showPassword = false
    this.errorMessage = [];
    this.authService.register({
      body: this.registrationRequest
    }).subscribe({
      next: () => {
        this.router.navigate(['activate-account'])
      },
      error: (err) => {
        if (err.error) {
          if (err.error.validationsErrors) {
            this.errorMessage = err.error.validationsErrors;
          } else if (err.error.error) {
            this.errorMessage = [err.error.error];
          }
        } else {
          this.errorMessage = ['An unexpected error occurred. Please try again.'];
        }
      }
    })
  }

  login() {
    this.router.navigate(['login'])
  }

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }
}
