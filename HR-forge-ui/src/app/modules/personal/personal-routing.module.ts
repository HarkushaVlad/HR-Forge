import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PersonalComponent } from './pages/personal/personal.component';
import { authGuard } from '../../services/guard/auth.guard';

const routes: Routes = [
  { path: '', component: PersonalComponent, canActivate: [authGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PersonalRoutingModule {}
