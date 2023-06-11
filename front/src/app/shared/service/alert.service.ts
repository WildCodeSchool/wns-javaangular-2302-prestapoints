import { Injectable } from '@angular/core';
import { Alert } from '../model/alert';
import { Subject } from 'rxjs';


@Injectable({
  providedIn: 'root',
})
export class AlertService {
  private alertSubject: Subject<any> = new Subject<any>();
  alerts = this.alertSubject.asObservable();

  private readonly storageKey = 'alert';

  alert?: Alert;

  constructor() {}

  setAlert(alert: Alert) {
    localStorage.setItem(this.storageKey, JSON.stringify(alert));
    this.showAlert(alert);
  }

  getAlert(): Alert {
    const alertData = localStorage.getItem(this.storageKey);
    return alertData ? JSON.parse(alertData) : null;
  }

  clearAlert(): void {
    localStorage.removeItem(this.storageKey);
  }

  showAlert(alert: Alert) {
    this.alertSubject.next(alert);
  }

}
