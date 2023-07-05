import { Component, Input, OnInit } from '@angular/core';
import { AlertEnum } from 'src/app/shared/enum/alert.enum';
import { ResponseApi } from 'src/app/shared/model/responseApi';
import { User } from 'src/app/shared/model/user';
import { AlertService } from 'src/app/shared/services/alert.service';
import { UserService } from 'src/app/shared/services/user.service';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss'],
})
export class TableComponent {
  @Input()
  public users?: User[];
  responseApi?: ResponseApi;

  constructor(
    private userService: UserService,
    private alertService: AlertService
  ) {}

  deleteUser(user: User) {
      this.userService.deleteUser(user).subscribe((response) => {
          this.responseApi = response;

      if (this.responseApi?.responseValid == true) {
        this.alertService.setAlert(
          AlertEnum.TYPE_SUCCESS,
          AlertEnum.MESSAGE_DELETE_SUCCESS,
          AlertEnum.TIME_MEDIUM
        );
      }  else {
        this.alertService.setAlert(
          AlertEnum.TYPE_DANGER,
          this.responseApi?.message ?? AlertEnum.ERROR,
          AlertEnum.TIME_INFINITY
        );
      }
    });
  }
}
