import { Type } from "./type";
import { Registration } from "./registration";
import { Location } from "./location";
import { Image } from "./image";

export class Prestation {

    public id? : number;
    public title? : string;
    public duration? : number;
    public addPoint? : number;
    public dateStart? : number;
    public dateEnd? : number;
    public state? : string;
    public description? : string;
    public maxUser? : number;
    public image? : Image[];
    public type?: Type;
    public location?: Location;
    public placeAvailable?: number;
    public littleDescription?: string;
    public practicalInformation?: string;
    public language?: string;
    public personalInfos?: string;
    public registration?: Registration[];
    

    constructor(){}



}
