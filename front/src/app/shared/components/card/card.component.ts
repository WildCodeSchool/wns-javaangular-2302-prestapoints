import { Component, Input, OnInit } from '@angular/core';
import { Prestation } from '../../model/Prestation';


@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss'],
})
  
export class CardComponent {
  @Input()
  public prestation?: Prestation;

  constructor() {}
}
