import { IPerson } from 'app/shared/model/person.model';
import { IFilm } from 'app/shared/model/film.model';

export interface IDirector {
  id?: number;
  alias?: string;
  person?: IPerson;
  film?: IFilm;
}

export class Director implements IDirector {
  constructor(public id?: number, public alias?: string, public person?: IPerson, public film?: IFilm) {}
}
