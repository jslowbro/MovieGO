import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IActor, Actor } from 'app/shared/model/actor.model';
import { ActorService } from './actor.service';
import { IPerson } from 'app/shared/model/person.model';
import { PersonService } from 'app/entities/person/person.service';
import { IFilm } from 'app/shared/model/film.model';
import { FilmService } from 'app/entities/film/film.service';

type SelectableEntity = IPerson | IFilm;

@Component({
  selector: 'jhi-actor-update',
  templateUrl: './actor-update.component.html'
})
export class ActorUpdateComponent implements OnInit {
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
    protected actorService: ActorService,
    protected personService: PersonService,
    protected filmService: FilmService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ actor }) => {
      this.updateForm(actor);

      this.personService
        .query({ filter: 'actor-is-null' })
        .pipe(
          map((res: HttpResponse<IPerson[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPerson[]) => {
          if (!actor.person || !actor.person.id) {
            this.people = resBody;
          } else {
            this.personService
              .find(actor.person.id)
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

  updateForm(actor: IActor): void {
    this.editForm.patchValue({
      id: actor.id,
      alias: actor.alias,
      person: actor.person,
      film: actor.film
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const actor = this.createFromForm();
    if (actor.id !== undefined) {
      this.subscribeToSaveResponse(this.actorService.update(actor));
    } else {
      this.subscribeToSaveResponse(this.actorService.create(actor));
    }
  }

  private createFromForm(): IActor {
    return {
      ...new Actor(),
      id: this.editForm.get(['id'])!.value,
      alias: this.editForm.get(['alias'])!.value,
      person: this.editForm.get(['person'])!.value,
      film: this.editForm.get(['film'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IActor>>): void {
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
