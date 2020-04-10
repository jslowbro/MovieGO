import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFilm } from 'app/shared/model/film.model';
import { FilmService } from './film.service';
import { FilmDeleteDialogComponent } from './film-delete-dialog.component';

@Component({
  selector: 'jhi-film',
  templateUrl: './film.component.html'
})
export class FilmComponent implements OnInit, OnDestroy {
  films?: IFilm[];
  eventSubscriber?: Subscription;

  constructor(protected filmService: FilmService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.filmService.query().subscribe((res: HttpResponse<IFilm[]>) => (this.films = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInFilms();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IFilm): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInFilms(): void {
    this.eventSubscriber = this.eventManager.subscribe('filmListModification', () => this.loadAll());
  }

  delete(film: IFilm): void {
    const modalRef = this.modalService.open(FilmDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.film = film;
  }
}
