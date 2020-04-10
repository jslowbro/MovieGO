import { IPersonContainer } from 'app/shared/model/person-container.model';

export interface IPerson {
  id?: number;
  name?: string;
  surname?: string;
  country?: string;
  personContainers?: IPersonContainer[];
}

export class Person implements IPerson {
  constructor(
    public id?: number,
    public name?: string,
    public surname?: string,
    public country?: string,
    public personContainers?: IPersonContainer[]
  ) {}
}
