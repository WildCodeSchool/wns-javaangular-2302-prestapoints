import { Registration } from "./registration";

export class Prestation {

  constructor(
    public title?: string,
    public duration?: string,
    public addPoint?: string,
    public dateStart?: string,
    public dateEnd?: string,
    public state?: string,
    public description?: string,
    public maxUser?: string,
    public image?: string,
    public category?: string,
    public type?: string,
   // public registration?: Registration[]
  ) {}
}
