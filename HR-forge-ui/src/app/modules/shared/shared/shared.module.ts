import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuComponent } from './components/menu/menu.component';
import { CollapseModule } from 'ngx-bootstrap/collapse';
import { RouterLink } from '@angular/router';
import { FooterComponent } from './components/footer/footer/footer.component';
import { TruncatePipe } from '../pipes/truncate.pipe';

@NgModule({
  declarations: [MenuComponent, FooterComponent, TruncatePipe],
  imports: [CommonModule, CollapseModule, RouterLink],
  exports: [MenuComponent, FooterComponent, TruncatePipe],
})
export class SharedModule {}
