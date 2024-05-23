import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { authGuard } from './services/guard/auth.guard';
import { loginGuard } from './services/guard/login.guard';
import { LogoutComponent } from './pages/logout/logout.component';
import { NotFoundComponent } from './pages/not-found/not-found/not-found.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [loginGuard],
  },
  {
    path: 'logout',
    component: LogoutComponent,
    canActivate: [authGuard],
  },
  {
    path: 'profile',
    loadChildren: () =>
      import('./modules/personal/main.module').then((m) => m.MainModule),
    canActivate: [authGuard],
  },
  {
    path: '**',
    component: NotFoundComponent,
    pathMatch: 'full',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
