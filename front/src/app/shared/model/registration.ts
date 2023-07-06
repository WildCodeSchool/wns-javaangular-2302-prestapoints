import { Prestation } from './prestation';
import { User } from './user';

export class Registration {
  constructor(
    public evaluation?: string,
    public comment?: string,
    public user?: User,
    public prestation?: Prestation
  ) {}
}
