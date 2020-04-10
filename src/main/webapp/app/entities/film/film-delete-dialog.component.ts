import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFilm } from 'app/shared/model/film.model';
import { FilmService } from './film.service';

@Component({
  templateUrl: './film-delete-dialog.component.html'
})
export class FilmDeleteDialogComponent {
  film?: IFilm;

  constructor(protected filmService: FilmService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.filmService.delete(id).subscribe(() => {
      this.eventManager.broadcast('filmListModification');
      this.activeModal.close();
    });
  }
}
