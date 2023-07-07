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

    getId(): number | undefined {
        return this.id;
      }
    
      getTitle(): string | undefined {
        return this.title;
      }
    
      getDuration(): string | undefined {
        return this.duration?.toString();
      }
    
      getAddPoint(): string | undefined {
        return this.addPoint?.toString();
      }
    
      getDateStart(): string | undefined {
        return this.dateStart?.toString();
      }
    
      getDateEnd(): string | undefined {
        return this.dateEnd?.toString();
      }
    
      getState(): string | undefined {
        return this.state;
      }
    
      getDescription(): string | undefined {
        return this.description;
      }
    
      getMaxUser(): string | undefined {
        return this.maxUser?.toString();
      }
    
      getImage(): Image[] | undefined {
        return this.image;
      }
    
      getType(): Type | undefined {
        return this.type;
      }
    
      getLocation(): Location | undefined {
        return this.location;
      }
    
      getPlaceAvailable(): number | undefined {
        return this.placeAvailable;
      }
    
      getLittleDescription(): string | undefined {
        return this.littleDescription;
      }
    
      getPracticalInformation(): string | undefined {
        return this.practicalInformation;
      }
    
      getLanguage(): string | undefined {
        return this.language;
      }
    
      getPersonalInfos(): string | undefined {
        return this.personalInfos;
      }
    
      getRegistration(): Registration[] | undefined {
        return this.registration;
      }

}
