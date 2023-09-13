import { Component } from '@angular/core';
import { User } from 'src/app/shared/model/user';
import { UserService } from 'src/app/shared/services/user.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
  users?: User[];

  constructor(private userService: UserService) {

  }
  ngOnInit(): void {
    this.userService.getUsers().subscribe((response) => {
      this.users = response;
    });
    }
}
