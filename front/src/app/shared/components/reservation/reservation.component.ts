import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Prestation } from '../../model/prestation';
import { DatePipe } from '@angular/common';
import { PrestationService } from '../../services/prestation.service';
import { AlertService } from 'src/app/shared/services/alert.service';
import { ResponseApi } from 'src/app/shared/model/responseApi';
import { AlertEnum } from 'src/app/shared/enum/alert.enum';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.scss']
})
export class ReservationComponent {
  @Input()
  public prestations?: Prestation[];
  
  @Output()
  public needToRefresh: EventEmitter<boolean> = new EventEmitter();

  responseApi?: ResponseApi;
  
  constructor(
    private prestationService: PrestationService,
    private alertService: AlertService) { }

  addRegistration(id: number | undefined) {
    this.prestationService.addRegistration(id).subscribe((response) => {
      this.responseApi = response;
      
      if (this.responseApi.responseValid == true) {
        this.alertService.setAlert(
          AlertEnum.TYPE_SUCCESS,
          AlertEnum.MESSAGE_RESERVATION_SUCCESS,
          AlertEnum.TIME_MEDIUM
        );

        this.needToRefresh.emit(true);

      } else {
        this.alertService.setAlert(
          AlertEnum.TYPE_DANGER,
          this.responseApi.message,
          AlertEnum.TIME_MEDIUM
        );
      }
    });
  }
}


