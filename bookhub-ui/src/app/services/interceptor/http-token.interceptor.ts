import {HttpEvent, HttpHandlerFn, HttpHeaders, HttpRequest} from '@angular/common/http';
import {TokenService} from "../token/token.service";
import {inject} from "@angular/core";
import {Observable} from "rxjs";

export function httpTokenInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>> {
  const tokenService = inject(TokenService);
  const token = tokenService.token;

  if (token) {
    const authReq = req.clone({
      headers: new HttpHeaders({
        Authorization: 'Bearer ' + token
      })
    });
    return next(authReq);
  }

  return next(req);
}
