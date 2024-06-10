import { Component, EventEmitter, Output, ViewChild } from '@angular/core';
import { EmployeeService } from '../../../../services/services/employee.service';
import { EmployeeResponse } from '../../../../services/models/employee-response';
import { EmployeeRequest } from '../../../../services/models/employee-request';
import { TokenService } from '../../../../services/token/token.service';
import { UpdatePasswordComponent } from '../update-password/update-password.component';

@Component({
  selector: 'app-employee-edit',
  templateUrl: './employee-edit.component.html',
  styleUrl: './employee-edit.component.scss',
})
export class EmployeeEditComponent {
  @Output() updateSuccess = new EventEmitter<void>();

  employee!: EmployeeResponse;
  resetEmployee!: EmployeeResponse;

  errorMsg: Array<string> = [];
  isLoading: boolean = true;
  isAdmin = false;
  isOwn: boolean = true;

  @ViewChild('employeeEditModal') employeeEditModal: any;

  @ViewChild(UpdatePasswordComponent)
  updatePasswordComponent!: UpdatePasswordComponent;

  constructor(
    private employeeService: EmployeeService,
    private tokenService: TokenService
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
    this.errorMsg = [];
    this.isLoading = false;
    this.employeeEditModal.show();
    this.isAdmin = this.tokenService.checkIsAdmin();
    this.isOwn = this.tokenService.checkIsOwn(this.employee);
  }

  onSubmit(): void {
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
          this.employeeEditModal.hide();
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
}
