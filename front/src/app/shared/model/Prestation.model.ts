import { Image } from "./Image";

export class Prestation {
    constructor(
        public id : string,
        public title : string,
        public duration : number,
        public addPoint : number,
        public dateStart : number,
        public dateEnd : number,
        public state : string,
        public description : string,
        public maxUser : string,
        public image : Image[]
    ){}

    
}