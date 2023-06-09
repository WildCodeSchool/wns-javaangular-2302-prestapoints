import { Type } from "./type";
import { Registration } from "./registration";
import { Location } from "./location";
import { Image } from "./image";

export class Prestation {

    public id? : number;
    public title? : string;
    public duration? : number;
    public addPoint? : number;
    public dateStartTimestamps? : number;
    //public dateStartString? : string;
    //public timeStartString? : string;
    public dateEnd? : number;
    public state? : string;
    public description? : string;
    public maxUser? : number;
    public images? : Image[];
    public type?: Type;
    public location?: Location;
    public placeAvailable?: number;
    public littleDescription?: string;
    public practicalInformation?: string;
    public language?: string;
    public personalInfos?: string;
    public registration?: Registration[];
    

    constructor(){}

    get getDuration(): string {
        if (this.duration != undefined){
            const durautionHour = this.duration/1000/60/60;
            console.log(durautionHour);
            return durautionHour.toString();
        }
        return '0';

    }

    get getDateStartString(): string {
        if (typeof this.dateStartTimestamps === 'number') {
            const dateStart = new Date(this.dateStartTimestamps);
            const day = dateStart.getDate().toString().padStart(2, '0');
            const month = (dateStart.getMonth() + 1).toString().padStart(2, '0');
            const year = dateStart.getFullYear().toString();
            const formattedDate = `${day}/${month}/${year}`;
            return formattedDate;
        }
        return 'dd/mm/yyyy'; 
    }
      
    get getTimeStartString(): string {
        if (typeof this.dateStartTimestamps === 'number') {
            const date = new Date(this.dateStartTimestamps);
            const hours = date.getHours().toString().padStart(2, '0');
            const minutes = date.getMinutes().toString().padStart(2, '0');
            return `${hours}:${minutes}`;
        }
        return "hh:mm";
          
    }

    get getDateTimeStartString(): string {
        if (typeof this.dateStartTimestamps === 'number') {
            const dateTime = new Date(this.dateStartTimestamps);    
            const day = dateTime.getDate().toString().padStart(2, '0');
            const month = (dateTime.getMonth() + 1).toString().padStart(2, '0');
            const year = dateTime.getFullYear().toString();
            const hours = dateTime.getHours().toString().padStart(2, '0');
            const minutes = dateTime.getMinutes().toString().padStart(2, '0');
      
        return `${day} ${month} ${year}, ${hours}:${minutes}`;
      }
      return "dd MM yyyy, HH:mm";
      
    }

}
