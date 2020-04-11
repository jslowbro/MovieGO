export interface IReview {
  id?: number;
  text?: any;
  value?: number;
  filmId?: number;
  userLogin?: string;
  userId?: number;
}

export class Review implements IReview {
  constructor(
    public id?: number,
    public text?: any,
    public value?: number,
    public filmId?: number,
    public userLogin?: string,
    public userId?: number
  ) {}
}
