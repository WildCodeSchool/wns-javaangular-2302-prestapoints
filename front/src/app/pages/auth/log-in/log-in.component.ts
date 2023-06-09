import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/core/service/auth/authentication.service';
import { AlertEnum } from 'src/app/shared/enum/alert.enum';
import { Alert } from 'src/app/shared/model/alert';
import { AlertService } from 'src/app/shared/services/alert.service';


@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.scss'],
})
export class LogInComponent implements OnInit {
  model: any = {};
  loading: boolean = false;
  error: String = '';

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private alertService: AlertService
  ) {}

  ngOnInit() {
    // reset login status
    this.authenticationService.logout();
  }

  login() {
    this.loading = true;
    this.authenticationService
      .login(this.model.username, this.model.password)
      .subscribe(
        (result) => {
          if (result) {
            console.log(
              'well done, you are logged with roles: ' +
                this.authenticationService.getRoles().join(', ')
            );
            this.alertService.setAlert(
              AlertEnum.TYPE_SUCCESS,
              AlertEnum.MESSAGE_LOGIN_SUCCESSED,
              AlertEnum.TIME_MEDIUM
            );
            this.router.navigate(['/prestation/formulaire']);
          } else {
            // login failed
            this.error = 'Username or password is incorrect';
            this.loading = false;
          }
        },
        (error: string) => {
          this.loading = false;
          console.error(error);
          this.alertService.setAlert(
            AlertEnum.TYPE_DANGER,
            AlertEnum.MESSAGE_LOGIN_FAILED,
            AlertEnum.TIME_MEDIUM
          );
          this.error = error;
        }
      );
  }
}
