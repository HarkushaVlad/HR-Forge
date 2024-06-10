import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MainRoutingModule } from './main-routing.module';
import { ProfileComponent } from './pages/profile/profile.component';
import { FormsModule } from '@angular/forms';
import { ModalModule } from 'ngx-bootstrap/modal';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { WorkspaceComponent } from './pages/workspace/workspace.component';
import { EmployeeCardComponent } from './components/employee-card/employee-card.component';
import { DepartmentCardComponent } from './components/department-card/department-card.component';
import { SharedModule } from '../shared/shared/shared.module';
import { PositionCardComponent } from './components/position-card/position-card.component';
import { UpdatePasswordComponent } from './components/update-password/update-password.component';
import { EmployeeEditComponent } from './components/employee-edit/employee-edit.component';
import { DepartmentEditComponent } from './components/department-edit/department-edit.component';
import { PositionEditComponent } from './components/position-edit/position-edit.component';

@NgModule({
  declarations: [
    ProfileComponent,
    WorkspaceComponent,
    EmployeeCardComponent,
    DepartmentCardComponent,
    PositionCardComponent,
    UpdatePasswordComponent,
    EmployeeEditComponent,
    DepartmentEditComponent,
    PositionEditComponent,
  ],
  imports: [
    CommonModule,
    MainRoutingModule,
    FormsModule,
    ModalModule,
    BsDatepickerModule,
    ModalModule.forRoot(),
    SharedModule,
  ],
})
export class MainModule {}
