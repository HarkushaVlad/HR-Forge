import { Component } from '@angular/core';
import { TokenService } from '../../services/token/token.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrl: './logout.component.scss',
})
export class LogoutComponent {
  constructor(
    private location: Location,
    private tokenService: TokenService
  ) {}

  confirmExit() {
    this.tokenService.removeToken();
    window.location.reload();
  }

  cancelExit() {
    this.location.back();
  }
}
