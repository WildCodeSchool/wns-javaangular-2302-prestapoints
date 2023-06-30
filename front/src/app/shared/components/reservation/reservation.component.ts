import { Component, Input, OnInit } from '@angular/core';
import { Prestation } from '../../model/prestation';
import { DatePipe } from '@angular/common';
import { PrestationService } from '../../services/prestation.service';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.scss']
})
export class ReservationComponent {
  
  @Input()
  public prestations?: Prestation[];

  constructor(
    private datePipe: DatePipe,
    private prestationService: PrestationService) {}

  addRegistration(id: number | undefined) {
    this.prestationService.addRegistration(id);

  }
  
}


