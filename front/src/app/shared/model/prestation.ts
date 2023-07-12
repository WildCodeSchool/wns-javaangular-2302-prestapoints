import { Type } from "./type";
import { Registration } from "./registration";
import { Location } from "./location";
import { SafeUrl } from "@angular/platform-browser";

export class Prestation {
  category: any;

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
    public littleDescription?: string,
    public practicalInformation?: string,
    public language?: string,
    public personalInfos?: string,
    public locationInfos?: string,
    public dateComment?: string,
    public authorComment?: string,
    public comment?: string,
    public videoUrl?: string,
    public type?: Type,
    public location?: Location,
    public tags? : string[],
    public url?: SafeUrl | string,
    // public registration?: Registration[]
  ) {}
}
