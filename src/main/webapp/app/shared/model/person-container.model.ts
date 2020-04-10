import { IPerson } from 'app/shared/model/person.model';
import { IFilm } from 'app/shared/model/film.model';
import { Role } from 'app/shared/model/enumerations/role.model';

export interface IPersonContainer {
  id?: number;
  role?: Role;
  person?: IPerson;
  film?: IFilm;
}

export class PersonContainer implements IPersonContainer {
  constructor(public id?: number, public role?: Role, public person?: IPerson, public film?: IFilm) {}
}
