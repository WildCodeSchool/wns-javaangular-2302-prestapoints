import { Component } from '@angular/core';
import { Role } from 'src/app/shared/enum/role.enum';
import { User } from 'src/app/shared/model/user';
import { UserService } from 'src/app/shared/services/user.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponent {
  users?: User[];

  constructor(private userService: UserService) {}
  ngOnInit(): void {
    this.userService.getUsers().subscribe((response) => {
      this.users = response;

      this.users.forEach((user) => {
        user.roles?.forEach((role) => {
          switch (role.name) {
            case Role.USER:
              role.slug = Role.SLUG_USER;
              break;
            case Role.ADMIN:
              role.slug = Role.SLUG_ADMIN;
              break;
          }
        });
      });
    });
  }
}
