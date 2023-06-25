import { Component, OnInit } from '@angular/core';
import '/node_modules/bootstrap/dist/js/bootstrap.min.js';
import { Alert } from './shared/model/alert';

import { Location } from '@angular/common';
import { AlertService } from './shared/services/alert.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  title = 'front';
  alerts: Alert[] = [];
  isVisible?: boolean;

  constructor(private alertService: AlertService, public location: Location) {}

  ngOnInit() {
    this.alertSubscription();
    this.isVisible = this.location.path() === '/auth' ? false : true;
  }

  alertSubscription() {
    this.alertService.alert$.subscribe((alerts) => {
      this.alerts = alerts;
    });
  }

  clearAlert() {
    this.alertService.clearAlert();
  }
}
