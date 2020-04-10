import { IReview } from 'app/shared/model/review.model';
import { IUser } from 'app/core/user/user.model';

export interface IComment {
  id?: number;
  text?: string;
  review?: IReview;
  user?: IUser;
}

export class Comment implements IComment {
  constructor(public id?: number, public text?: string, public review?: IReview, public user?: IUser) {}
}
