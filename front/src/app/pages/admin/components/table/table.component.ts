import { Component, Input, OnInit } from '@angular/core';
import { AlertEnum } from 'src/app/shared/enum/alert.enum';
import { ResponseApi } from 'src/app/shared/model/responseApi';
import { User } from 'src/app/shared/model/user';
import { AlertService } from 'src/app/shared/services/alert.service';
import { UserService } from 'src/app/shared/services/user.service';
import { Role } from 'src/app/shared/enum/role.enum';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss'],
})
export class TableComponent implements OnInit {

  public users?: User[];

  responseApi?: ResponseApi;
  selectedUsers: User[] = [];

  constructor(
    private userService: UserService,
      private alertService: AlertService,
  ) {}
  ngOnInit(): void {
    this.getUsers();
  }

  deleteUser(user: User) {
    this.userService.deleteUser(user).subscribe((response) => {
      this.responseApi = response;

      if (this.responseApi?.responseValid == true) {
        this.alertService.setAlert(
          AlertEnum.TYPE_SUCCESS,
          AlertEnum.MESSAGE_DELETE_SUCCESS,
          AlertEnum.TIME_MEDIUM
          );
          this.getUsers();
      } else {
        this.alertService.setAlert(
          AlertEnum.TYPE_DANGER,
          this.responseApi?.message ?? AlertEnum.ERROR,
          AlertEnum.TIME_INFINITY
        );
      }
    });
  }

  deleteUsers() {
    if (this.selectedUsers.length > 0) {
      this.userService.deleteUsers(this.selectedUsers).subscribe((response) => {
        this.responseApi = response;

        if (this.responseApi?.responseValid == true) {
          this.alertService.setAlert(
            AlertEnum.TYPE_SUCCESS,
            AlertEnum.MESSAGE_DELETE_SUCCESS,
            AlertEnum.TIME_MEDIUM
            );
            this.getUsers();
        } else {
          this.alertService.setAlert(
            AlertEnum.TYPE_DANGER,
            this.responseApi?.message ?? AlertEnum.ERROR,
            AlertEnum.TIME_INFINITY
          );
        }
      });
    }
  }

  addToDeleteList(cbTarget: EventTarget | null, user: User) {
    const target = cbTarget as HTMLInputElement;
    if (target.checked) {
      this.selectedUsers.push(user);
    } else {
      const index = this.selectedUsers.indexOf(user);
      if (index > -1) {
        this.selectedUsers.splice(index, 1);
      }
    }
  }

  getUsers() {
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
