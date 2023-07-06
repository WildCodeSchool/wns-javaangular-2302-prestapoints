import { Component, Input } from '@angular/core';
import { Prestation } from 'src/app/shared/model/prestation';

@Component({
  selector: 'app-card-details',
  templateUrl: './card-details.component.html',
  styleUrls: ['./card-details.component.scss']
})
export class CardDetailsComponent {
  @Input() prestation?: Prestation;
}
