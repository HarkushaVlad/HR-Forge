import { Component, Input, ViewChild } from '@angular/core';
import { PersonalService } from '../../../../services/services/personal.service';
import { EmployeeService } from '../../../../services/services/employee.service';
import { PasswordRequest } from '../../../../services/models/password-request';
import { AdminPasswordRequest } from '../../../../services/models/admin-password-request';
import { TokenService } from '../../../../services/token/token.service';

@Component({
  selector: 'app-update-password',
  templateUrl: './update-password.component.html',
  styleUrls: ['./update-password.component.scss'],
})
export class UpdatePasswordComponent {
  @Input() isPersonal!: boolean;
  @Input() employeeId?: number;

  oldPassword = '';
  newPassword = '';
  confirmPassword = '';
  errorMsg: Array<string> = [];
  isLoading = false;

  @ViewChild('updatePasswordModal') updatePasswordModal: any;

  constructor(
    private personalService: PersonalService,
    private employeeService: EmployeeService,
    private tokenService: TokenService
  ) {}

  openUpdatePasswordDialog(): void {
    this.resetForm();
    this.updatePasswordModal.show();
  }

  onSubmit(): void {
    if (this.newPassword !== this.confirmPassword) {
      this.errorMsg = ['Паролі не співпадають'];
      this.newPassword = '';
      this.confirmPassword = '';
      return;
    }

    this.isLoading = true;

    if (this.isPersonal) {
      const passwordRequest: PasswordRequest = {
        oldPassword: this.oldPassword,
        newPassword: this.newPassword,
      };

      this.personalService
        .updatePersonalPassword({ body: passwordRequest })
        .subscribe({
          next: () => {
            this.isLoading = false;
            this.tokenService.removeToken();
            window.location.reload();
          },
          error: (err) => {
            this.isLoading = false;
            this.resetForm();

            if (err.error.validationErrors) {
              this.errorMsg = err.error.validationErrors;
            } else {
              this.errorMsg.push(err.error.error);
            }
          },
        });
    } else {
      if (!this.employeeId) {
        this.errorMsg = ['Робітник не обраний'];
        return;
      }

      const adminPasswordRequest: AdminPasswordRequest = {
        newPassword: this.newPassword,
      };

      this.employeeService
        .updateEmployeePassword({
          id: this.employeeId,
          body: adminPasswordRequest,
        })
        .subscribe({
          next: () => {
            this.isLoading = false;
            this.resetForm();
            this.updatePasswordModal.hide();
          },
          error: (err) => {
            this.isLoading = false;
            this.oldPassword = '';
            this.newPassword = '';
            this.confirmPassword = '';

            if (err.error.validationErrors) {
              this.errorMsg = err.error.validationErrors;
            } else {
              this.errorMsg.push(err.error.error);
            }
          },
        });
    }
  }

  resetForm(): void {
    this.errorMsg = [];
    this.oldPassword = '';
    this.newPassword = '';
    this.confirmPassword = '';
  }
}
