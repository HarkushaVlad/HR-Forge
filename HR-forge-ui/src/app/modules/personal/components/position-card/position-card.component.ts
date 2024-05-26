import { Component, EventEmitter, Input, Output } from '@angular/core';
import { PositionResponse } from '../../../../services/models/position-response';

@Component({
  selector: 'app-position-card',
  templateUrl: './position-card.component.html',
  styleUrl: './position-card.component.scss',
})
export class PositionCardComponent {
  @Input() position!: PositionResponse;
  @Output() edit = new EventEmitter<PositionResponse>();
  isCopied = false;

  editPosition() {
    this.edit.emit(this.position);
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
