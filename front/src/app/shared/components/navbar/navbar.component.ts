import { Component, Input, OnInit } from '@angular/core';
import { LocalStorageService } from '../../services/localStorage.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  @Input()
  isVisible?: boolean
  inputVisible?: boolean;
  inputNotvisible?: boolean;

  constructor(private localstorageService: LocalStorageService) { }

  ngOnInit() {
    this.checkUserLoggedIn()
  }

  checkUserLoggedIn() {
    const value = this.localstorageService.getItem('currentUser');
    if (value != null) {
      this.inputVisible = false
      this.inputNotvisible = true;
    }else {
      this.inputVisible = true
      this.inputNotvisible = false;
    }
  }

}
