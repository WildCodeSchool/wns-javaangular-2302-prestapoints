import { Type } from "./type";
import { Registration } from "./registration";
import { Location } from "./location";

export class Prestation {

  constructor(
    public id?: number,
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
    public placeAvailable?: string,
    public littleDescription?: string,
    public practicalInformation?: string,
    public language?: string,
    public personalInfos?: string,
    public registration?: Registration[]
  ) {}
}
