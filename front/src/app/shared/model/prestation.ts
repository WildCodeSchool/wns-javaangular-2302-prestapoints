import { Type } from "./type";
import { Registration } from "./registration";
import { Location } from "./location";
import { Image } from "./image";

export class Prestation {

    public id? : number;
    public title? : string;
    public duration? : string;
    public addPoint? : string;
    public dateStart? : string;
    public dateEnd? : string;
    public state? : string;
    public description? : string;
    public maxUser? : string;
    public image? : Image[];
    public type?: Type;
    public location?: Location;
    public placeAvailable?: string;
    public littleDescription?: string;
    public practicalInformation?: string;
    public language?: string;
    public personalInfos?: string;
    public registration?: Registration[];
    

    constructor(){}



}
