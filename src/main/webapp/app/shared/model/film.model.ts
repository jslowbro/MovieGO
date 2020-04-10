import { IRating } from 'app/shared/model/rating.model';
import { IReview } from 'app/shared/model/review.model';
import { IPersonContainer } from 'app/shared/model/person-container.model';

export interface IFilm {
  id?: number;
  title?: string;
  description?: string;
  ratings?: IRating[];
  reviews?: IReview[];
  personContainers?: IPersonContainer[];
}

export class Film implements IFilm {
  constructor(
    public id?: number,
    public title?: string,
    public description?: string,
    public ratings?: IRating[],
    public reviews?: IReview[],
    public personContainers?: IPersonContainer[]
  ) {}
}
