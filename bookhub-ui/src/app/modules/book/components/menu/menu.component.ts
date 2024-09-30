import {Component} from '@angular/core';
import {KeycloakService} from "../../../../services/keycloak/keycloak.service";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss'
})
export class MenuComponent {
  username: string | undefined;

  constructor(
    private keycloakService: KeycloakService
  ) {
    this.username = this.keycloakService.getUsername();
  }

  async logout() {
    await this.keycloakService.logout();
  }

  accountManagement() {
    this.keycloakService.manageAccount();
  }

}
