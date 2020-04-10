import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPersonContainer } from 'app/shared/model/person-container.model';
import { PersonContainerService } from './person-container.service';

@Component({
  templateUrl: './person-container-delete-dialog.component.html'
})
export class PersonContainerDeleteDialogComponent {
  personContainer?: IPersonContainer;

  constructor(
    protected personContainerService: PersonContainerService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.personContainerService.delete(id).subscribe(() => {
      this.eventManager.broadcast('personContainerListModification');
      this.activeModal.close();
    });
  }
}
