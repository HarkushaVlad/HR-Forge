import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuComponent } from './components/menu/menu.component';
import { CollapseModule } from 'ngx-bootstrap/collapse';
import { RouterLink } from '@angular/router';
import { FooterComponent } from './components/footer/footer/footer.component';
import { TruncatePipe } from '../pipes/truncate.pipe';
import { FieldNameFormatPipe } from '../pipes/field-name-format.pipe';

@NgModule({
  declarations: [
    MenuComponent,
    FooterComponent,
    TruncatePipe,
    FieldNameFormatPipe,
  ],
  imports: [CommonModule, CollapseModule, RouterLink],
  exports: [MenuComponent, FooterComponent, TruncatePipe, FieldNameFormatPipe],
})
export class SharedModule {}
