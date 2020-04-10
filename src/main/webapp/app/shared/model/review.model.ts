import { IFilm } from 'app/shared/model/film.model';

export interface IReview {
  id?: number;
  text?: any;
  value?: number;
  film?: IFilm;
}

export class Review implements IReview {
  constructor(public id?: number, public text?: any, public value?: number, public film?: IFilm) {}
}
