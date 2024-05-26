import { Component, EventEmitter, Input, Output } from '@angular/core';
import { EmployeeResponse } from '../../../../services/models/employee-response';

@Component({
  selector: 'app-employee-card',
  templateUrl: './employee-card.component.html',
  styleUrl: './employee-card.component.scss',
})
export class EmployeeCardComponent {
  @Input() employee!: EmployeeResponse;
  @Output() edit = new EventEmitter<EmployeeResponse>();
  isCopied = false;

  editEmployee() {
    this.edit.emit(this.employee);
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

  protected readonly toString = toString;
}
