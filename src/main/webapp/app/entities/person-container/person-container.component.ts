import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPersonContainer } from 'app/shared/model/person-container.model';
import { PersonContainerService } from './person-container.service';
import { PersonContainerDeleteDialogComponent } from './person-container-delete-dialog.component';

@Component({
  selector: 'jhi-person-container',
  templateUrl: './person-container.component.html'
})
export class PersonContainerComponent implements OnInit, OnDestroy {
  personContainers?: IPersonContainer[];
  eventSubscriber?: Subscription;

  constructor(
    protected personContainerService: PersonContainerService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.personContainerService.query().subscribe((res: HttpResponse<IPersonContainer[]>) => (this.personContainers = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPersonContainers();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPersonContainer): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPersonContainers(): void {
    this.eventSubscriber = this.eventManager.subscribe('personContainerListModification', () => this.loadAll());
  }

  delete(personContainer: IPersonContainer): void {
    const modalRef = this.modalService.open(PersonContainerDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.personContainer = personContainer;
  }
}
