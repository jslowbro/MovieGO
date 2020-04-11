import { Role } from 'app/shared/model/enumerations/role.model';

export interface IPersonContainer {
  id?: number;
  role?: Role;
  personId?: number;
  filmId?: number;
}

export class PersonContainer implements IPersonContainer {
  constructor(public id?: number, public role?: Role, public personId?: number, public filmId?: number) {}
}
