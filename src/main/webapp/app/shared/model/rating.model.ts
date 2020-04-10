import { IReview } from 'app/shared/model/review.model';
import { IFilm } from 'app/shared/model/film.model';

export interface IRating {
  id?: number;
  value?: number;
  author?: string;
  review?: IReview;
  film?: IFilm;
}

export class Rating implements IRating {
  constructor(public id?: number, public value?: number, public author?: string, public review?: IReview, public film?: IFilm) {}
}
