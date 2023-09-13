import { Prestation } from "./prestation";

export class Image {

    public id?: number;
    public data?: File | undefined;
    public prestation?: Prestation;
    public url?: string;
    
    constructor() {
    }

    public getimageUrl(): string {
        if(this.data!=undefined ){
            //return  URL.createObjectURL(this.data);
            return "https://images.pexels.com/photos/301703/pexels-photo-301703.jpeg?auto=com";
        }
        return "https://images.pexels.com/photos/301703/pexels-photo-301703.jpeg?auto=com";
    }

}
