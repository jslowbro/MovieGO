export interface IFilm {
  id?: number;
  title?: string;
  description?: string;
}

export class Film implements IFilm {
  constructor(public id?: number, public title?: string, public description?: string) {}
}
