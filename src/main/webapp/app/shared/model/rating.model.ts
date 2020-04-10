import { IFilm } from 'app/shared/model/film.model';
import { IReview } from 'app/shared/model/review.model';

export interface IRating {
  id?: number;
  value?: number;
  film?: IFilm;
  review?: IReview;
}

export class Rating implements IRating {
  constructor(public id?: number, public value?: number, public film?: IFilm, public review?: IReview) {}
}
