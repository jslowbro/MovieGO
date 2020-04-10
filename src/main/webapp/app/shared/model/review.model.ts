import { IComment } from 'app/shared/model/comment.model';
import { IRating } from 'app/shared/model/rating.model';
import { IFilm } from 'app/shared/model/film.model';

export interface IReview {
  id?: number;
  author?: string;
  text?: string;
  comments?: IComment[];
  ratings?: IRating[];
  film?: IFilm;
}

export class Review implements IReview {
  constructor(
    public id?: number,
    public author?: string,
    public text?: string,
    public comments?: IComment[],
    public ratings?: IRating[],
    public film?: IFilm
  ) {}
}
