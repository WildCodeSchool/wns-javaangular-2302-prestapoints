import { Component, Input } from '@angular/core';
import { Alert } from '../model/alert';
import { AlertService } from '../service/alert.service';

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.scss'],
})
export class AlertComponent {
  @Input()
  alert?: Alert;

  constructor(private alertService: AlertService) {}

  clearAlert() {
    this.alertService.clearAlert();
  }
}
