import {
  Component,
  EventEmitter,
  OnInit,
  Output,
  ViewChild,
} from '@angular/core';
import { DepartmentService } from '../../../../services/services/department.service';
import { TokenService } from '../../../../services/token/token.service';
import { DepartmentResponse } from '../../../../services/models/department-response';
import { DepartmentRequest } from '../../../../services/models/department-request';
import { DeleteEntityComponent } from '../delete-entity/delete-entity.component';

@Component({
  selector: 'app-department-edit',
  templateUrl: './department-edit.component.html',
  styleUrl: './department-edit.component.scss',
})
export class DepartmentEditComponent implements OnInit {
  @Output() updateSuccess = new EventEmitter<void>();

  department!: DepartmentResponse;
  resetDepartment!: DepartmentResponse;

  errorMsg: Array<string> = [];
  isLoading: boolean = true;
  isAdmin = false;

  @ViewChild('departmentEditModal') departmentEditModal: any;

  @ViewChild(DeleteEntityComponent)
  deleteEntityComponent!: DeleteEntityComponent;

  constructor(
    private departmentService: DepartmentService,
    private tokenService: TokenService
  ) {}

  ngOnInit(): void {
    this.isAdmin = this.tokenService.checkIsAdmin();
  }

  openEditDepartmentDialog(department: DepartmentResponse): void {
    this.department = { ...department };
    this.resetDepartment = { ...department };
    this.errorMsg = [];
    this.isLoading = false;
    this.departmentEditModal.show();
    this.isAdmin = this.tokenService.checkIsAdmin();
  }

  onSubmit(): void {
    this.isLoading = true;
    this.errorMsg = [];

    const departmentRequest: DepartmentRequest = {
      name: this.department.name ?? '',
      description: this.department.description ?? '',
    };

    this.departmentService
      .updateDepartment({
        'id-or-name': this.department.departmentId?.toString() ?? '0',
        body: departmentRequest,
      })
      .subscribe({
        next: () => {
          this.isLoading = false;
          this.departmentEditModal.hide();
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
    this.openEditDepartmentDialog(this.resetDepartment);
  }
}
