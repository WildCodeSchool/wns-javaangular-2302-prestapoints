import { Component, EventEmitter, Output } from '@angular/core';
import { Alert } from 'src/app/shared/model/alert';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']
})
export class AuthComponent {
  alertToSend?: Alert;

  @Output()
  alert: EventEmitter<Alert> = new EventEmitter();

  onReceiveAlert(event: Alert): void {
    this.alertToSend = event;
    this.sendAlert();
  }

  sendAlert() {
    this.alert.emit(this.alertToSend);
  }
}
