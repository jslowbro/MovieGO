import { IFilm } from 'app/shared/model/film.model';
import { IUser } from 'app/core/user/user.model';

export interface IReview {
  id?: number;
  text?: any;
  value?: number;
  film?: IFilm;
  user?: IUser;
}

export class Review implements IReview {
  constructor(public id?: number, public text?: any, public value?: number, public film?: IFilm, public user?: IUser) {}
}
