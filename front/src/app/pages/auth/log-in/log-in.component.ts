import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/core/service/auth/authentication.service';
import { AlertEnum } from 'src/app/shared/enum/alert-enum';
import { Alert } from 'src/app/shared/model/alert';
import { AlertService } from 'src/app/shared/service/alert.service';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.scss']
})
export class LogInComponent implements OnInit {

  model: any = {};
  loading: boolean = false;
  error: String = '';
  alert: Alert = new Alert();

  @Output()
  alertToSend: EventEmitter<Alert> = new EventEmitter();

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private alertService: AlertService) { }
  ngOnInit() {
    // reset login status
    this.authenticationService.logout();
  }

  login() {
    this.loading = true;
    this.authenticationService.login(this.model.username, this.model.password)
      .subscribe(
        result => {
          if (result) {
            console.log("well done, you are logged with roles: " + this.authenticationService.getRoles().join(', '));
            this.sendAlert(AlertEnum.TYPE_SUCCESS, AlertEnum.MESSAGE_LOGIN_SUCCESSED, true);
            this.router.navigate(['/prestation/formulaire']);
          } else {
            // login failed
            this.error = 'Username or password is incorrect';
            this.loading = false;
          }
        }, (error: string) => {
          this.loading = false;
          console.error(error);
          this.sendAlert(AlertEnum.TYPE_DANGER, AlertEnum.MESSAGE_LOGIN_FAILED, true);
          this.error = error;
        });
  }

  sendAlert(type: string, message: string, timer: boolean): void {
      this.alert.type = type;
      this.alert.message = message;
      this.alert.timer = timer;
    this.alertService.setAlert(this.alert);
  }
}
