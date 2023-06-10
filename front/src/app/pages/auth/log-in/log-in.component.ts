import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/core/service/auth/authentication.service';
import { Alert } from 'src/app/shared/model/alert';

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
    private authenticationService: AuthenticationService) { }
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
            this.alert.type = "success";
            this.alert.message = "Bonjour, vous êtes connecté";
            this.alert.timer = true;
            this.sendAlert();
            this.startAlertTimer();
            this.router.navigate(['/home']);
          } else {
            // login failed
            this.error = 'Username or password is incorrect';
            this.loading = false;

          }
        }, (error: string) => {
          this.loading = false;
          console.error(error);
          this.alert.type = "danger";
          this.alert.message = "L'identifiant ou le mot de passe est incorrect";
          this.alert.timer = true;
          this.sendAlert();
          this.error = error;
        });
  }

  sendAlert(): void {
    this.alertToSend.emit(this.alert);
  }

  startAlertTimer() {
    setTimeout(() => {
      this.alert.timer = false; // Réinitialiser l'alerte pour la faire disparaître
      this.sendAlert();
    }, 6000); // 2000 millisecondes = 3 secondes
  }

}
