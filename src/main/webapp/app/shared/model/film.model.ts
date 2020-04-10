import { IPersonContainer } from 'app/shared/model/person-container.model';

export interface IFilm {
  id?: number;
  title?: string;
  description?: string;
  personcontainers?: IPersonContainer[];
}

export class Film implements IFilm {
  constructor(public id?: number, public title?: string, public description?: string, public personcontainers?: IPersonContainer[]) {}
}
