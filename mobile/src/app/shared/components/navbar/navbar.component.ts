import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent implements OnInit {

  @Input()
  isVisible?: boolean;
  inputVisible?: boolean;
  inputNotvisible?: boolean;

  constructor(
    // private localstorageService: LocalStorageService,
    // private logoutService: AuthenticationService,
    // private alertService: AlertService,
    private router: Router
  ) { }

  ngOnInit() {
    this.checkUserLoggedIn()
  }

  checkUserLoggedIn() {
    // const value = this.localstorageService.getItem('currentUser');
    // if (value != null) {
    //   this.inputVisible = false
    //   this.inputNotvisible = true;
    // } else {
    //   this.inputVisible = true
    //   this.inputNotvisible = false;
    // }
  }

  logout() {
    // this.router.navigate(['/'])
    // this.logoutService.logout();
    // this.checkUserLoggedIn()
    // this.alertService.setAlert(
    //   AlertEnum.TYPE_DANGER,
    //   AlertEnum.MESSAGE_LOGOUT_SUCCESSED,
    //   AlertEnum.TIME_MEDIUM
    // )
  }
}