import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { TokenService } from '../token/token.service';

export const hrGuard: CanActivateFn = (route, state) => {
  const tokenService = inject(TokenService);
  const router = inject(Router);
  const authorities = tokenService.getAuthorities();

  if (
    authorities.includes('Системний адміністратор') ||
    authorities.includes('Менеджер з людських ресурсів')
  ) {
    return true;
  } else {
    router.navigate(['no-authorities']);
    return false;
  }
};
