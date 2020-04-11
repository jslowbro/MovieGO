import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRating, Rating } from 'app/shared/model/rating.model';
import { RatingService } from './rating.service';
import { IFilm } from 'app/shared/model/film.model';
import { FilmService } from 'app/entities/film/film.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

type SelectableEntity = IFilm | IUser;

@Component({
  selector: 'jhi-rating-update',
  templateUrl: './rating-update.component.html'
})
export class RatingUpdateComponent implements OnInit {
  isSaving = false;
  films: IFilm[] = [];
  users: IUser[] = [];

  editForm = this.fb.group({
    id: [],
    value: [],
    filmId: [],
    userId: []
  });

  constructor(
    protected ratingService: RatingService,
    protected filmService: FilmService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rating }) => {
      this.updateForm(rating);

      this.filmService.query().subscribe((res: HttpResponse<IFilm[]>) => (this.films = res.body || []));

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));
    });
  }

  updateForm(rating: IRating): void {
    this.editForm.patchValue({
      id: rating.id,
      value: rating.value,
      filmId: rating.filmId,
      userId: rating.userId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const rating = this.createFromForm();
    if (rating.id !== undefined) {
      this.subscribeToSaveResponse(this.ratingService.update(rating));
    } else {
      this.subscribeToSaveResponse(this.ratingService.create(rating));
    }
  }

  private createFromForm(): IRating {
    return {
      ...new Rating(),
      id: this.editForm.get(['id'])!.value,
      value: this.editForm.get(['value'])!.value,
      filmId: this.editForm.get(['filmId'])!.value,
      userId: this.editForm.get(['userId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRating>>): void {
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
