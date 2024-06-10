import { Component, EventEmitter, Output, ViewChild } from '@angular/core';
import { EmployeeService } from '../../../../services/services/employee.service';
import { EmployeeResponse } from '../../../../services/models/employee-response';
import { EmployeeRequest } from '../../../../services/models/employee-request';
import { TokenService } from '../../../../services/token/token.service';
import { UpdatePasswordComponent } from '../update-password/update-password.component';
import { DeleteEntityComponent } from '../delete-entity/delete-entity.component';
import { AuthenticationService } from '../../../../services/services/authentication.service';
import { RegistrationRequest } from '../../../../services/models/registration-request';

@Component({
  selector: 'app-employee-edit',
  templateUrl: './employee-edit.component.html',
  styleUrl: './employee-edit.component.scss',
})
export class EmployeeEditComponent {
  @Output() updateSuccess = new EventEmitter<void>();

  employee!: EmployeeResponse;
  resetEmployee!: EmployeeResponse;

  password = '';
  repeatPassword = '';

  errorMsg: Array<string> = [];
  isEditing = true;
  isLoading = true;
  isAdmin = false;
  isOwn = true;

  @ViewChild('employeeEditModal') employeeEditModal: any;

  @ViewChild(UpdatePasswordComponent)
  updatePasswordComponent!: UpdatePasswordComponent;

  @ViewChild(DeleteEntityComponent)
  deleteEntityComponent!: DeleteEntityComponent;

  constructor(
    private employeeService: EmployeeService,
    private tokenService: TokenService,
    private authService: AuthenticationService
  ) {}

  openEditEmployeeDialog(employee: EmployeeResponse): void {
    this.employee = {
      ...employee,
      birthDate: this.formatDate(employee.birthDate),
      hireDate: this.formatDate(employee.hireDate),
    };
    this.resetEmployee = {
      ...employee,
      birthDate: this.formatDate(employee.birthDate),
      hireDate: this.formatDate(employee.hireDate),
    };
    this.isEditing = true;
    this.errorMsg = [];
    this.isLoading = false;
    this.employeeEditModal.show();
    this.isAdmin = this.tokenService.checkIsAdmin();
    this.isOwn = this.tokenService.checkIsOwn(this.employee);
  }

  openAddEmployeeDialog(): void {
    this.clear();
    this.isEditing = false;
    this.errorMsg = [];
    this.isLoading = false;
    this.employeeEditModal.show();
    this.isAdmin = this.tokenService.checkIsAdmin();
    this.isOwn = this.tokenService.checkIsOwn(this.employee);
  }

  onUpdate(): void {
    this.isLoading = true;
    this.errorMsg = [];

    const employeeRequest: EmployeeRequest = {
      firstName: this.employee.firstName ?? '',
      lastName: this.employee.lastName ?? '',
      birthDate: this.employee.birthDate ?? '',
      email: this.employee.email ?? '',
      salary: this.employee.salary ?? 0,
      hireDate: this.employee.hireDate ?? '',
      positionName: this.employee.positionName ?? '',
      departmentName: this.employee.departmentName ?? '',
    };

    this.employeeService
      .updateEmployee({
        'id-or-email': this.resetEmployee.email ?? '',
        body: employeeRequest,
      })
      .subscribe({
        next: () => {
          this.isLoading = false;
          this.close();
          this.updateSuccess.emit();
        },
        error: (err) => {
          this.isLoading = false;
          if (err.error.validationErrors) {
            this.errorMsg = err.error.validationErrors;
          } else {
            this.errorMsg.push(err.error.error);
          }
        },
      });
  }

  onAdd(): void {
    this.isLoading = true;
    this.errorMsg = [];

    if (this.password !== this.repeatPassword) {
      this.errorMsg.push('Passwords does not match');
      this.isLoading = false;
      return;
    }

    const registrationRequest: RegistrationRequest = {
      firstName: this.employee.firstName ?? '',
      lastName: this.employee.lastName ?? '',
      birthDate: this.employee.birthDate ?? '',
      email: this.employee.email ?? '',
      salary: this.employee.salary ?? 0,
      hireDate: this.employee.hireDate ?? '',
      positionName: this.employee.positionName ?? '',
      departmentName: this.employee.departmentName ?? '',
      password: this.password,
    };

    this.authService
      .register({
        body: registrationRequest,
      })
      .subscribe({
        next: () => {
          this.isLoading = false;
          this.close();
          this.updateSuccess.emit();
        },
        error: (err) => {
          this.isLoading = false;
          if (err.error.validationErrors) {
            this.errorMsg = err.error.validationErrors;
          } else {
            this.errorMsg.push(err.error.error);
          }
        },
      });
  }

  resetForm(): void {
    this.openEditEmployeeDialog(this.resetEmployee);
  }

  private formatDate(date: string | undefined): string {
    if (!date) return '';
    const parsedDate = new Date(date);
    const year = parsedDate.getFullYear();
    const month = ('0' + (parsedDate.getMonth() + 1)).slice(-2);
    const day = ('0' + parsedDate.getDate()).slice(-2);
    return `${year}-${month}-${day}`;
  }

  clear() {
    this.employee = {
      firstName: '',
      lastName: '',
      birthDate: '',
      email: '',
      salary: 0,
      hireDate: '',
      positionName: '',
      departmentName: '',
    };

    this.password = '';
    this.repeatPassword = '';
  }

  close() {
    this.employeeEditModal.hide();
  }
}
