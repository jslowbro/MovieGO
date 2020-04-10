import { IFilm } from 'app/shared/model/film.model';
import { IUser } from 'app/core/user/user.model';

export interface IRating {
  id?: number;
  value?: number;
  film?: IFilm;
  user?: IUser;
}

export class Rating implements IRating {
  constructor(public id?: number, public value?: number, public film?: IFilm, public user?: IUser) {}
}
