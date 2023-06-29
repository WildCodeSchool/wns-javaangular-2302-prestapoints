import { Component, Input } from '@angular/core';
import { Prestation } from '../../model/prestation';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.scss']
})
export class ReservationComponent {
  @Input()
  public prestations?: Prestation[];

  constructor(private datePipe: DatePipe) { }

}
