import {Component} from '@angular/core';
import {KeycloakService} from "../../../../services/keycloak/keycloak.service";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss'
})
export class MenuComponent {

  constructor(
    private keycloakService: KeycloakService
  ) {
  }

  async logout() {
    await this.keycloakService.logout();
  }

}
