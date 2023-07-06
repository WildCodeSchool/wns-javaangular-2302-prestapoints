import { Prestation } from './prestation';
import { Registration } from './registration';

export class User {
  constructor(
    public firstname?: string | null,
    public lastname?: string | null,
    public email?: string | null,
    public password?: string | null,
    public phone?: string | null,
    public prestations?: Prestation[] | null,
    public registration?: Registration | null
  ) {}
}
