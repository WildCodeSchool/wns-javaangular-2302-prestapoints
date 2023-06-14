import { Injectable } from '@angular/core';
import { Alert } from '../model/alert';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AlertService {
  private alertSubject: Subject<any> = new Subject<any>();
  private readonly storageKey = 'alert';
  public alert$ = this.alertSubject.asObservable();
  private alert: Alert = new Alert();
  private existingAlerts: Alert[] = [];

  constructor() {}

  setAlert(
    type: string,
    message: string,
    timer: boolean,
    duration: number
  ): void {
    this.alert.type = type;
    this.alert.message = message;
    this.alert.timer = timer;

    this.existingAlerts = this.getAlerts() || [];
    this.existingAlerts.push(this.alert);
    localStorage.setItem(this.storageKey, JSON.stringify(this.existingAlerts));
    this.showAlert(duration);
  }

  getAlerts(): Alert[] {
    const alerts = localStorage.getItem(this.storageKey);
    return alerts ? JSON.parse(alerts) : null;
  }

  clearAlert(): void {
    this.existingAlerts = this.getAlerts() || [];
    if (this.existingAlerts.length > 0) {
      this.existingAlerts.pop();
    }
    localStorage.setItem(this.storageKey, JSON.stringify(this.existingAlerts));
    this.alertSubject.next(this.existingAlerts.length > 0 ? this.existingAlerts : null);
  }

  // clear the popup alert after the time duration
  showAlert(duration: number): void {
    this.alertSubject.next(this.getAlerts());
    setTimeout(() => {
      this.clearAlert();
    }, duration);
  }
}
