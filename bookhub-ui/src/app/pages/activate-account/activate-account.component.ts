import {Component} from '@angular/core';
import {AuthenticationService} from "../../services/services/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-activate-account',
  templateUrl: './activate-account.component.html',
  styleUrl: './activate-account.component.scss'
})
export class ActivateAccountComponent {

  message = '';
  isValid = true;
  submitted = false;

  constructor(
    private router: Router,
    private authService: AuthenticationService
  ) {
  }

  private activateAccount(token: string) {
    this.authService.activateAccount({
      token
    }).subscribe({
      next: () => {
        this.message = 'Your account has been successfully activated.\n You can now log in to access your account.';
        this.submitted = true;
        this.isValid = true;
      },
      error: () => {
        this.message = 'The activation token is either expired or invalid.\n Please request a new activation link and try again.';
        this.submitted = true;
        this.isValid = false;
      }
    });
  }

  onCodeCompleted(token: string) {
    this.activateAccount(token);
  }

  redirectToLogin() {
    this.router.navigate(['login']);
  }
}
