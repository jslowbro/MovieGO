export interface IRating {
  id?: number;
  value?: number;
  filmId?: number;
  userLogin?: string;
  userId?: number;
}

export class Rating implements IRating {
  constructor(public id?: number, public value?: number, public filmId?: number, public userLogin?: string, public userId?: number) {}
}
