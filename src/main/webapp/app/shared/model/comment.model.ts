import { IReview } from 'app/shared/model/review.model';

export interface IComment {
  id?: number;
  text?: string;
  review?: IReview;
}

export class Comment implements IComment {
  constructor(public id?: number, public text?: string, public review?: IReview) {}
}
