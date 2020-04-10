import { IPerson } from 'app/shared/model/person.model';
import { IFilm } from 'app/shared/model/film.model';
import { Role } from 'app/shared/model/enumerations/role.model';

export interface IPersonContainer {
  id?: number;
  role?: Role;
  film?: string;
  person?: IPerson;
  films?: IFilm[];
}

export class PersonContainer implements IPersonContainer {
  constructor(public id?: number, public role?: Role, public film?: string, public person?: IPerson, public films?: IFilm[]) {}
}
