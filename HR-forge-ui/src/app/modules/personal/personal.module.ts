import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PersonalRoutingModule } from './personal-routing.module';
import { PersonalComponent } from './pages/personal/personal.component';
import { FormsModule } from '@angular/forms';
import { ModalModule } from 'ngx-bootstrap/modal';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';

@NgModule({
  declarations: [PersonalComponent],
  imports: [
    CommonModule,
    PersonalRoutingModule,
    FormsModule,
    ModalModule,
    BsDatepickerModule,
    ModalModule.forRoot(),
  ],
})
export class PersonalModule {}
