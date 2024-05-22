import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuComponent } from './components/menu/menu.component';
import { CollapseModule } from 'ngx-bootstrap/collapse';
import { RouterLink } from '@angular/router';

@NgModule({
  declarations: [MenuComponent],
  imports: [CommonModule, CollapseModule, RouterLink],
  exports: [MenuComponent],
})
export class SharedModule {}
