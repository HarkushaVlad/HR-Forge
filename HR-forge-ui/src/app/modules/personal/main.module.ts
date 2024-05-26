import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MainRoutingModule } from './main-routing.module';
import { ProfileComponent } from './pages/profile/profile.component';
import { FormsModule } from '@angular/forms';
import { ModalModule } from 'ngx-bootstrap/modal';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { WorkspaceComponent } from './pages/workspace/workspace.component';

@NgModule({
  declarations: [ProfileComponent, WorkspaceComponent],
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
