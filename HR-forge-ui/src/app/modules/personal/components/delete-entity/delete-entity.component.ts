import { Component, EventEmitter, Output, ViewChild } from '@angular/core';
import { DepartmentResponse } from '../../../../services/models/department-response';
import { DepartmentService } from '../../../../services/services/department.service';
import { EmployeeResponse } from '../../../../services/models/employee-response';
import { PositionResponse } from '../../../../services/models/position-response';
import { PositionService } from '../../../../services/services/position.service';
import { EmployeeService } from '../../../../services/services/employee.service';

@Component({
  selector: 'app-delete-entity',
  templateUrl: './delete-entity.component.html',
  styleUrl: './delete-entity.component.scss',
})
export class DeleteEntityComponent {
  @Output() deleteSuccess = new EventEmitter<void>();

  employee!: EmployeeResponse;
  department!: DepartmentResponse;
  position!: PositionResponse;

  isApprove = false;
  errorMsg: Array<string> = [];
  isLoading: boolean = true;

  @ViewChild('deleteEntityModal') deleteEntityModal: any;

  constructor(
    private departmentService: DepartmentService,
    private positionService: PositionService,
    private employeeService: EmployeeService
  ) {}

  openDeleteEntityDialog(
    entity: PositionResponse | DepartmentResponse | EmployeeResponse
  ): void {
    if ('positionId' in entity) {
      this.position = entity;
    }

    if ('departmentId' in entity) {
      this.department = entity;
    }

    if ('employeeId' in entity) {
      this.employee = entity;
    }

    this.isApprove = false;
    this.errorMsg = [];
    this.isLoading = false;
    this.deleteEntityModal.show();
  }

  onSubmit(): void {
    this.isLoading = true;
    this.errorMsg = [];

    if (this.employee) {
      this.employeeService
        .deleteEmployee({
          'id-or-email': this.employee.employeeId?.toString() ?? '0',
        })
        .subscribe({
          next: () => {
            this.isLoading = false;
            this.deleteEntityModal.hide();
            this.deleteSuccess.emit();
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

    if (this.department) {
      this.departmentService
        .deleteDepartment({
          'id-or-name': this.department.departmentId?.toString() ?? '0',
        })
        .subscribe({
          next: () => {
            this.isLoading = false;
            this.deleteEntityModal.hide();
            this.deleteSuccess.emit();
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

    if (this.position) {
      this.positionService
        .deletePosition({
          'id-or-name': this.position.positionId?.toString() ?? '0',
        })
        .subscribe({
          next: () => {
            this.isLoading = false;
            this.deleteEntityModal.hide();
            this.deleteSuccess.emit();
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
  }
}
