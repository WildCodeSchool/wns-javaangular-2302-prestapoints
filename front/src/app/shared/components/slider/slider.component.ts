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
    public currentIndex = 0;
    public prestationsToDisplay: Prestation[] = [];
  
    constructor() { }

    prev() {
        if (this.prestationsApi?.length != null) {
          this.currentIndex = 
            this.currentIndex === 0
              ? this.prestationsApi.length - 1
              : this.currentIndex - 1;
        }
      }
    
      next() {
        if (this.prestationsApi?.length != null) {
          this.currentIndex =
            this.currentIndex === this.prestationsApi.length - 1
              ? 0
              : this.currentIndex + 1;
        }
      }
  }