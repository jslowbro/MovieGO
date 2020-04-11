export interface IComment {
  id?: number;
  text?: string;
  reviewId?: number;
  userLogin?: string;
  userId?: number;
}

export class Comment implements IComment {
  constructor(public id?: number, public text?: string, public reviewId?: number, public userLogin?: string, public userId?: number) {}
}
