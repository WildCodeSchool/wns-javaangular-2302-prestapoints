import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/core/service/auth/authentication.service';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.scss']
})
export class LogInComponent implements OnInit{

  model: any = {};
  loading: boolean = false;
  error: String = '';

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService) { }
  ngOnInit() {
    // reset login status
    this.authenticationService.logout();
  }

  login() {
    console.log("coucou");
    console.log(this.model.username);
    console.log(this.model.password);
    
    this.loading = true;
    this.authenticationService.login(this.model.username, this.model.password)
      .subscribe(
        result => {
          if (result) {
              console.log("well done, you are logged with roles: " + this.authenticationService.getRoles().join(', '));
              // login successful
              alert("gooooodddd");
            } else {
              // login failed
              this.error = 'Username or password is incorrect';
              this.loading = false;
              alert("not gooooodddd");
            }
      }, (error: string) => {
        this.loading = false;
        console.error(error);
        this.error = error;
      });
  }
}
