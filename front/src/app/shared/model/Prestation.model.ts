export class Prestation {
    constructor(
        public id : number,
        public title : string,
        public duration : string,
        public dateStart : Date,
        public dateEnd : Date,
        public state : string,
        public description : string,
        public maxUser : string,
        public image : File,
    ){}

}