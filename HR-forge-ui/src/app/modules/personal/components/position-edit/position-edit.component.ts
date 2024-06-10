import {
  Component,
  EventEmitter,
  OnInit,
  Output,
  ViewChild,
} from '@angular/core';
import { PositionResponse } from '../../../../services/models/position-response';
import { PositionService } from '../../../../services/services/position.service';
import { TokenService } from '../../../../services/token/token.service';
import { PositionRequest } from '../../../../services/models/position-request';
import { DeleteEntityComponent } from '../delete-entity/delete-entity.component';

@Component({
  selector: 'app-position-edit',
  templateUrl: './position-edit.component.html',
  styleUrl: './position-edit.component.scss',
})
export class PositionEditComponent implements OnInit {
  @Output() updateSuccess = new EventEmitter<void>();

  position!: PositionResponse;
  resetPosition!: PositionResponse;

  errorMsg: Array<string> = [];
  isLoading: boolean = true;
  isAdmin = false;

  @ViewChild('positionEditModal') positionEditModal: any;

  @ViewChild(DeleteEntityComponent)
  deleteEntityComponent!: DeleteEntityComponent;

  constructor(
    private positionService: PositionService,
    private tokenService: TokenService
  ) {}

  ngOnInit(): void {
    this.isAdmin = this.tokenService.checkIsAdmin();
  }

  openEditPositionDialog(position: PositionResponse): void {
    this.position = { ...position };
    this.resetPosition = { ...position };
    this.errorMsg = [];
    this.isLoading = false;
    this.positionEditModal.show();
    this.isAdmin = this.tokenService.checkIsAdmin();
  }

  onSubmit(): void {
    this.isLoading = true;
    this.errorMsg = [];

    const positionRequest: PositionRequest = {
      name: this.position.name ?? '',
      description: this.position.description ?? '',
    };

    this.positionService
      .updatePosition({
        'id-or-name': this.position.positionId?.toString() ?? '0',
        body: positionRequest,
      })
      .subscribe({
        next: () => {
          this.isLoading = false;
          this.positionEditModal.hide();
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
    this.openEditPositionDialog(this.resetPosition);
  }
}
