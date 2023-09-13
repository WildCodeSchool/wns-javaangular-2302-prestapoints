import { Component, Input, OnInit } from '@angular/core';
import { Prestation } from '../../model/prestation';

@Component({
  selector: 'app-card-details-mini',
  templateUrl: './card-details-mini.component.html',
  styleUrls: ['./card-details-mini.component.scss']
})
export class CardDetailsMiniComponent {

  @Input() public prestation?: Prestation;

  constructor() { }

  ngOnInit() {
    if (this.prestation) {
      console.log('Prestation', this.prestation);
    } else {
      console.log('Prestation is not defined.');
    }
  }
  
}
