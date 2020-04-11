import { Moment } from 'moment';

export interface IFilm {
  id?: number;
  title?: string;
  description?: string;
  releaseDate?: Moment;
}

export class Film implements IFilm {
  constructor(public id?: number, public title?: string, public description?: string, public releaseDate?: Moment) {}
}
