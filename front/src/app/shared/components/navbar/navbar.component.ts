import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { LocalStorageService } from '../../services/localStorage.service';
import { AuthenticationService } from 'src/app/core/service/auth/authentication.service';
import { AlertService } from '../../services/alert.service';
import { AlertEnum } from '../../enum/alert.enum';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit, OnDestroy {

  @Input()
  isVisible?: boolean;
  inputVisible?: boolean;
  inputSeConnecter?: boolean;
  private isLoggedInSubscription: Subscription = new Subscription();

  constructor(
    private localstorageService: LocalStorageService,
    private logoutService: AuthenticationService,
    private alertService: AlertService,
    private router: Router,
    private authService: AuthenticationService
  ) { 
    this.inputVisible = false;
    this.inputSeConnecter = true;
  }

  ngOnInit() {
    // this.checkUserLoggedIn()
    this.isLoggedInSubscription = this.authService.isLoggedIn().subscribe(loggedIn => {
      console.log("value 1111111111111111111");
      console.log(loggedIn);
      if (loggedIn) {
        this.inputVisible = false;
        this.inputSeConnecter = true;
      } else {
        this.inputVisible = true;
        this.inputSeConnecter = false;
      }
    });
  }

  // checkUserLoggedIn() {
  //   const value = this.localstorageService.getItem('currentUser');
  //   console.log("value >>>>>>>>>>");
  //   console.log(value);
  //   if (value != null) {
  //     this.inputVisible = false
  //     this.inputSeConnecter = true;
  //   } else {
  //     this.inputVisible = true
  //     this.inputSeConnecter = false;
  //   }
  // }

  ngOnDestroy() {
    // Se désabonner pour éviter les fuites de mémoire
    this.isLoggedInSubscription.unsubscribe();
  }

  logout() {
    this.router.navigate(['/']);
    this.logoutService.logout();
    this.alertService.setAlert(
      AlertEnum.TYPE_DANGER,
      AlertEnum.MESSAGE_LOGOUT_SUCCESSED,
      AlertEnum.TIME_MEDIUM
    )
  }
}
