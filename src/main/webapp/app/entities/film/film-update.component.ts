import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IFilm, Film } from 'app/shared/model/film.model';
import { FilmService } from './film.service';

@Component({
  selector: 'jhi-film-update',
  templateUrl: './film-update.component.html'
})
export class FilmUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    title: [],
    description: [],
    releaseDate: []
  });

  constructor(protected filmService: FilmService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ film }) => {
      if (!film.id) {
        const today = moment().startOf('day');
        film.releaseDate = today;
      }

      this.updateForm(film);
    });
  }

  updateForm(film: IFilm): void {
    this.editForm.patchValue({
      id: film.id,
      title: film.title,
      description: film.description,
      releaseDate: film.releaseDate ? film.releaseDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const film = this.createFromForm();
    if (film.id !== undefined) {
      this.subscribeToSaveResponse(this.filmService.update(film));
    } else {
      this.subscribeToSaveResponse(this.filmService.create(film));
    }
  }

  private createFromForm(): IFilm {
    return {
      ...new Film(),
      id: this.editForm.get(['id'])!.value,
      title: this.editForm.get(['title'])!.value,
      description: this.editForm.get(['description'])!.value,
      releaseDate: this.editForm.get(['releaseDate'])!.value
        ? moment(this.editForm.get(['releaseDate'])!.value, DATE_TIME_FORMAT)
        : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFilm>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
