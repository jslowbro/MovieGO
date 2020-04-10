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
import { IReview } from 'app/shared/model/review.model';
import { ReviewService } from 'app/entities/review/review.service';

type SelectableEntity = IFilm | IReview;

@Component({
  selector: 'jhi-rating-update',
  templateUrl: './rating-update.component.html'
})
export class RatingUpdateComponent implements OnInit {
  isSaving = false;
  films: IFilm[] = [];
  reviews: IReview[] = [];

  editForm = this.fb.group({
    id: [],
    value: [],
    film: [],
    review: []
  });

  constructor(
    protected ratingService: RatingService,
    protected filmService: FilmService,
    protected reviewService: ReviewService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rating }) => {
      this.updateForm(rating);

      this.filmService.query().subscribe((res: HttpResponse<IFilm[]>) => (this.films = res.body || []));

      this.reviewService.query().subscribe((res: HttpResponse<IReview[]>) => (this.reviews = res.body || []));
    });
  }

  updateForm(rating: IRating): void {
    this.editForm.patchValue({
      id: rating.id,
      value: rating.value,
      film: rating.film,
      review: rating.review
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
      film: this.editForm.get(['film'])!.value,
      review: this.editForm.get(['review'])!.value
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
