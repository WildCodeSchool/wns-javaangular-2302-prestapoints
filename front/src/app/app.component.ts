import { Component, Input, OnInit } from '@angular/core';
import '/node_modules/bootstrap/dist/js/bootstrap.min.js';
import { Alert } from './shared/model/alert';
import { ActivatedRoute } from '@angular/router';
import { AlertService } from './shared/service/alert.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  title = 'front';
  alert: Alert = new Alert();

  constructor(private alertService: AlertService) {}

  ngOnInit() {
    this.alertSubscription();
  }

  alertSubscription() {
    this.alertService.alert$.subscribe((alert) => {
      this.alert = alert;
    });
  }

  clearAlert() {
    this.alertService.clearAlert();
  }
}
