import { Image } from "./Image";
import { Location } from "./location";
import { Registration } from "./registration";
import { Type } from "./type";

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
    public image? : Image;
    public type?: Type;
    public location?: Location;
    public placeAvailable?: number;
    
    public registration?: Registration[];
    
    
    constructor(){}


}