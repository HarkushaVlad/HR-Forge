import { Component, EventEmitter, Input, Output } from '@angular/core';
import { DepartmentResponse } from '../../../../services/models/department-response';

@Component({
  selector: 'app-department-card',
  templateUrl: './department-card.component.html',
  styleUrl: './department-card.component.scss',
})
export class DepartmentCardComponent {
  @Input() department!: DepartmentResponse;
  @Output() edit = new EventEmitter<DepartmentResponse>();
  isCopied = false;

  editDepartment() {
    this.edit.emit(this.department);
  }

  copyToClipboard(textToCopy: string | undefined | null): void {
    if (textToCopy) {
      navigator.clipboard.writeText(textToCopy);
      this.isCopied = true;
      setTimeout(() => {
        this.isCopied = false;
      }, 500);
    }
  }
}
