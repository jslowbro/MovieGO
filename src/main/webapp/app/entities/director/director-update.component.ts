import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IDirector, Director } from 'app/shared/model/director.model';
import { DirectorService } from './director.service';
import { IPerson } from 'app/shared/model/person.model';
import { PersonService } from 'app/entities/person/person.service';
import { IFilm } from 'app/shared/model/film.model';
import { FilmService } from 'app/entities/film/film.service';

type SelectableEntity = IPerson | IFilm;

@Component({
  selector: 'jhi-director-update',
  templateUrl: './director-update.component.html'
})
export class DirectorUpdateComponent implements OnInit {
  isSaving = false;
  people: IPerson[] = [];
  films: IFilm[] = [];

  editForm = this.fb.group({
    id: [],
    alias: [],
    person: [],
    film: []
  });

  constructor(
    protected directorService: DirectorService,
    protected personService: PersonService,
    protected filmService: FilmService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ director }) => {
      this.updateForm(director);

      this.personService
        .query({ filter: 'director-is-null' })
        .pipe(
          map((res: HttpResponse<IPerson[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPerson[]) => {
          if (!director.person || !director.person.id) {
            this.people = resBody;
          } else {
            this.personService
              .find(director.person.id)
              .pipe(
                map((subRes: HttpResponse<IPerson>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IPerson[]) => (this.people = concatRes));
          }
        });

      this.filmService.query().subscribe((res: HttpResponse<IFilm[]>) => (this.films = res.body || []));
    });
  }

  updateForm(director: IDirector): void {
    this.editForm.patchValue({
      id: director.id,
      alias: director.alias,
      person: director.person,
      film: director.film
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const director = this.createFromForm();
    if (director.id !== undefined) {
      this.subscribeToSaveResponse(this.directorService.update(director));
    } else {
      this.subscribeToSaveResponse(this.directorService.create(director));
    }
  }

  private createFromForm(): IDirector {
    return {
      ...new Director(),
      id: this.editForm.get(['id'])!.value,
      alias: this.editForm.get(['alias'])!.value,
      person: this.editForm.get(['person'])!.value,
      film: this.editForm.get(['film'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDirector>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
