import { Component, Input } from "@angular/core";
import { Prestation } from "../../model/prestation";



@Component({
    selector: 'app-slider',
    templateUrl: './slider.component.html',
    styleUrls: ['./slider.component.scss'],
  })
    
  export class SliderComponent {
    @Input()
    public prestationsApi?: Prestation[];
   
  
    constructor() { }

    slideConfig = {
      "slidesToShow": 5,
      "slidesToScroll": 5,
      "arrows": false,
      "dots": true,
      "autoplay": true,
      "autoplaySpeed": 5000,
      "pauseOnHover": true,
      "infinite": true, 
    }
  }

