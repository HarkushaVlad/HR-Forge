// profile.component.ts
import { Component, OnInit, ViewChild } from '@angular/core';
import { PersonalService } from '../../../../services/services/personal.service';
import { EmployeeResponse } from '../../../../services/models/employee-response';
import { PersonalRequest } from '../../../../services/models/personal-request';
import { UpdatePasswordComponent } from '../../components/update-password/update-password.component';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent implements OnInit {
  personalRequest: PersonalRequest = {
    birthDate: '',
    email: '',
    firstName: '',
    lastName: '',
  };
  errorMsg: Array<String> = [];

  personalInformation: EmployeeResponse = {
    departmentName: 'Can not load department name',
    positionName: 'Can not load position name',
    firstName: 'Can not load name',
    lastName: 'Can not load surname',
    birthDate: new Date(1990, 1, 1).toString(),
    email: 'Can not load email',
    salary: 0,
    hireDate: new Date(1990, 1, 1).toString(),
  };

  isLoading: boolean = true;
  isError: boolean = false;

  fieldName?: string;
  currentField?: string;
  fieldValue?: any;
  isDateField?: boolean;
  @ViewChild('editModal') editModal: any;
  @ViewChild(UpdatePasswordComponent)
  updatePasswordComponent!: UpdatePasswordComponent;

  constructor(private personalService: PersonalService) {}

  ngOnInit(): void {
    this.personalService.getPersonal().subscribe({
      next: (res) => {
        this.personalInformation = res;
        this.isLoading = false;
      },
      error: (err) => {
        console.log(err);
        this.isLoading = false;
        this.isError = true;
      },
    });
  }

  openEditDialog(fieldName: string, field: string): void {
    this.errorMsg = [];
    this.personalRequest = {
      birthDate: this.personalInformation.birthDate ?? '',
      email: this.personalInformation.email ?? '',
      firstName: this.personalInformation.firstName ?? '',
      lastName: this.personalInformation.lastName ?? '',
    };

    this.fieldName = fieldName;
    this.currentField = field;
    this.isDateField = field === 'birthDate' || field === 'hireDate';
    const fieldValue =
      this.personalInformation[field as keyof EmployeeResponse];

    if (this.isDateField && fieldValue) {
      this.fieldValue = new Date(fieldValue);
    } else {
      this.fieldValue = fieldValue;
    }

    this.editModal.show();
  }

  onSubmit(): void {
    this.errorMsg = [];
    this.personalRequest[this.currentField as keyof PersonalRequest] =
      this.fieldValue;

    this.isLoading = true;

    this.personalService
      .updatePersonal({ body: this.personalRequest })
      .subscribe({
        next: () => {
          this.personalInformation[this.currentField as keyof PersonalRequest] =
            this.fieldValue;
          this.editModal.hide();
          this.isLoading = false;
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
