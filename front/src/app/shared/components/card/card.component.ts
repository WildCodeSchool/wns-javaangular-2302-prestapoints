import { Component, Input, OnInit } from '@angular/core';
import { Prestation } from '../../model/prestation';

//import { PrestationDto } from './back/src/main/java/fr/dto/PrestationDto.java';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss'],
})
export class CardComponent implements OnInit {
  @Input() public prestation?: Prestation;

  constructor() {}

  ngOnInit() {}
}
