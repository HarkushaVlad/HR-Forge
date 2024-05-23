import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MainRoutingModule } from './main-routing.module';
import { ProfileComponent } from './pages/profile/profile.component';
import { FormsModule } from '@angular/forms';
import { ModalModule } from 'ngx-bootstrap/modal';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';

@NgModule({
  declarations: [ProfileComponent],
  imports: [
    CommonModule,
    MainRoutingModule,
    FormsModule,
    ModalModule,
    BsDatepickerModule,
    ModalModule.forRoot(),
  ],
})
export class MainModule {}
