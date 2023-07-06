import { Component, Input, OnInit } from '@angular/core';
import { Prestation } from '../../model/prestation';

@Component({
  selector: 'app-card-details-mini',
  templateUrl: './card-details-mini.component.html',
  styleUrls: ['./card-details-mini.component.scss']
})
export class CardDetailsMiniComponent implements OnInit {

  @Input() prestation?: Prestation;

  constructor() { }

  ngOnInit() {
  }

}
