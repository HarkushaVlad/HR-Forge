import { Component, OnDestroy, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { TokenService } from '../../../../../services/token/token.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss',
})
export class MenuComponent implements OnInit, OnDestroy {
  isCollapsed = true;
  isLoginPage = true;
  isHr = false;
  private routerSubscription: Subscription | undefined;

  constructor(
    private router: Router,
    private tokenService: TokenService
  ) {}

  ngOnInit(): void {
    this.routerSubscription = this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.checkIsLoginPage();
        this.checkIsHr();
      }
    });
  }

  ngOnDestroy(): void {
    if (this.routerSubscription) {
      this.routerSubscription.unsubscribe();
    }
  }

  private checkIsLoginPage() {
    const currUrl = this.router.routerState.snapshot.url;
    this.isLoginPage = currUrl.startsWith('/login');
  }

  private checkIsHr() {
    const authorities = this.tokenService.getAuthorities();
    this.isHr =
      authorities.includes('System Administrator') ||
      authorities.includes('HR Manager');
  }
}
