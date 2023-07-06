import { Type } from "./type";
import { Registration } from "./registration";
import { Location } from "./location";
import { SafeUrl } from "@angular/platform-browser";

export class Prestation {

  constructor(
    public id?: string,
    public title?: string,
    public duration?: string,
    public addPoint?: string,
    public dateStart?: string,
    public dateEnd?: string,
    public state?: string,
    public description?: string,
    public maxUser?: string,
    public image?: string,
    public type?: Type,
    public location?: Location,
    public tags? : string[],
    public url?: SafeUrl | string,

    
   // public registration?: Registration[]
  ) {}
}
