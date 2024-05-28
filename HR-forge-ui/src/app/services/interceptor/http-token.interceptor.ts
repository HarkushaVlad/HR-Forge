import { Injectable } from '@angular/core';
import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { TokenService } from '../token/token.service';
import { Router } from '@angular/router';

@Injectable()
export class HttpTokenInterceptor implements HttpInterceptor {
  constructor(
    private tokenService: TokenService,
    private router: Router
  ) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const token = this.tokenService.token;
    let authReq = req;

    if (token) {
      authReq = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
        },
      });
    }

    return next.handle(authReq).pipe(
      catchError((error) => {
        if (error.status === 403) {
          this.tokenService.removeToken();
          this.router.navigate(['/login']);
        }
        return throwError(error);
      })
    );
  }
}
