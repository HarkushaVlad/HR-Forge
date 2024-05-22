import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from '../../../../../services/token/token.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss',
})
export class MenuComponent implements OnInit {
  isCollapsed = true;
  isLoginPage = true;
  isHr = false;

  constructor(
    private router: Router,
    private tokenService: TokenService
  ) {}

  ngOnInit(): void {
    this.isLoginPage = this.checkIsLoginPage();
    this.isHr = this.checkIsHr();
  }

  private checkIsLoginPage(): boolean {
    const currUrl = this.router.routerState.snapshot.url;
    return currUrl.startsWith('/login');
  }

  private checkIsHr(): boolean {
    const authorities = this.tokenService.getAuthorities();
    return (
      authorities.includes('System Administrator') ||
      authorities.includes('HR Manager')
    );
  }
}
