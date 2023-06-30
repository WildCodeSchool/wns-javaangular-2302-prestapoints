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
        //public image : File
    ){}

    
    
    get getId(): string {
        return this.id;
    }

    set setId(id: string) {
        this.id = id;
    }

    get getTitle(): string {
        return this.title;
    }

    set setTitle(title: string) {
        this.title = title;
    }

    get getDuration(): number {
        return this.duration;
    }

    set setDuration(duration: number) {
        this.duration = duration;
    }

    get getAddPoint(): number {
        return this.addPoint;
    }

    set setAddPoint(addPoint: number) {
        this.addPoint = addPoint;
    }

    get getDateStart(): number {
        return this.dateStart;
    }

    set setDateStart(dateStart: number) {
        this.dateStart = dateStart;
    }

    get getDateEnd(): Date {
        const date = new Date(this.dateEnd);
        //const dateString = date.toLocaleString();
        return date;
    }

    set setDateEnd(dateEnd: number) {
        this.dateEnd = dateEnd;
    }

    get getState(): string {
        return this.state;
    }

    set setState(state: string) {
        this.state = state;
    }

    get getDescription(): string {
        return this.description;
    }

    set setDescription(description: string) {
        this.description = description;
    }

    get getMaxUser(): string {
        return this.maxUser;
    }

    set setMaxUser(maxUser: string) {
        this.maxUser = maxUser;
    }

    //get getImage(): File[] {
    //    return this.image;
    //}
//
    //set setImage(image: File[]) {
    //    this.image = image;
    //}
}