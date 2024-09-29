import {HttpEvent, HttpHandlerFn, HttpHeaders, HttpRequest} from '@angular/common/http';
import {inject} from "@angular/core";
import {Observable} from "rxjs";
import {KeycloakService} from "../keycloak/keycloak.service";

export function httpTokenInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>> {
  const keycloakService = inject(KeycloakService);
  const token = keycloakService.keycloak.token;

  if (token) {
    const authReq = req.clone({
      headers: new HttpHeaders({
        Authorization: `Bearer ${token}`
      })
    });
    return next(authReq);
  }

  return next(req);
}
