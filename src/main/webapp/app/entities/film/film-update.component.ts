import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFilm, Film } from 'app/shared/model/film.model';
import { FilmService } from './film.service';
import { IPersonContainer } from 'app/shared/model/person-container.model';
import { PersonContainerService } from 'app/entities/person-container/person-container.service';

@Component({
  selector: 'jhi-film-update',
  templateUrl: './film-update.component.html'
})
export class FilmUpdateComponent implements OnInit {
  isSaving = false;
  personcontainers: IPersonContainer[] = [];

  editForm = this.fb.group({
    id: [],
    title: [],
    description: [],
    personContainer: []
  });

  constructor(
    protected filmService: FilmService,
    protected personContainerService: PersonContainerService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ film }) => {
      this.updateForm(film);

      this.personContainerService.query().subscribe((res: HttpResponse<IPersonContainer[]>) => (this.personcontainers = res.body || []));
    });
  }

  updateForm(film: IFilm): void {
    this.editForm.patchValue({
      id: film.id,
      title: film.title,
      description: film.description,
      personContainer: film.personContainer
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
      personContainer: this.editForm.get(['personContainer'])!.value
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

  trackById(index: number, item: IPersonContainer): any {
    return item.id;
  }
}
