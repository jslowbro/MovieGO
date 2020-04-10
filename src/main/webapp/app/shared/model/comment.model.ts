import { IReview } from 'app/shared/model/review.model';

export interface IComment {
  id?: number;
  author?: string;
  text?: string;
  review?: IReview;
}

export class Comment implements IComment {
  constructor(public id?: number, public author?: string, public text?: string, public review?: IReview) {}
}
