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
    this.clearAlert();
    localStorage.setItem(this.storageKey, JSON.stringify(this.alert));
    this.showAlert(duration);
  }

  getAlert(): Alert {
    const alert = localStorage.getItem(this.storageKey);
    return alert ? JSON.parse(alert) : null;
  }

  clearAlert(): void {
    localStorage.removeItem(this.storageKey);
    this.alertSubject.next(null);
  }

  // clear the popup alert after the time duration
  showAlert(duration: number): void {
    this.alertSubject.next(this.getAlert());
    setTimeout(() => {
      this.clearAlert();
    }, duration);
  }
}
