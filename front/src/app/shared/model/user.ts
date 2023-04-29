import { Prestation } from './prestation';

export class User {
  constructor(
    public id?: number,
    public firstname?: string,
    public registrations?: Prestation[]
  ) {}
}
