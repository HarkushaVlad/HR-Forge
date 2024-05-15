import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root',
})
export class TokenService {
  set token(token: string) {
    localStorage.setItem('token', token);
  }

  get token() {
    return localStorage.getItem('token') as string;
  }

  private isTokenExpired(): boolean {
    if (!this.token) {
      this.token = this.token;
    }
    if (!this.token) {
      return true;
    }

    const decodedTokenExp = jwtDecode(this.token).exp;

    if (decodedTokenExp === undefined) {
      return true;
    } else {
      const expirationDate = decodedTokenExp * 1000;
      return expirationDate < Date.now();
    }
  }

  isTokenValid(): boolean {
    const isValid =
      this.token !== null && this.token !== undefined && !this.isTokenExpired();
    if (isValid) {
      return true;
    } else {
      this.removeToken();
      return false;
    }
  }

  removeToken() {
    localStorage.removeItem('token');
  }
}
