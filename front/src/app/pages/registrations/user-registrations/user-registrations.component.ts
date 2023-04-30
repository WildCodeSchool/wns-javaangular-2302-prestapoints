import { Component } from '@angular/core';
import { User } from 'src/app/shared/model/user';
import { UserRegistrationsService } from '../services/user-registrations.service';
import { Prestation } from 'src/app/shared/model/prestation';
import { ActivatedRoute } from '@angular/router';
import { isEmpty, map } from 'rxjs';

@Component({
  selector: 'app-user-registrations',
  templateUrl: './user-registrations.component.html',
  styleUrls: ['./user-registrations.component.scss'],
})
export class UserRegistrationsComponent {
  public prestations?: Prestation[];
  public userId?: string;

  constructor(
    public UserRegistrationsService: UserRegistrationsService,
    private route: ActivatedRoute
  ) {
    this.prestations = [];
  }

  ngOnInit() {
    this.getIdByParam();
    this.getListOfPrestations();
  }

  getIdByParam() {
    this.route.paramMap.subscribe((params) => {
      this.userId = params.get('id') ?? '';
    });
  }

  getListOfPrestations() {
    if (this.userId == '' || this.userId == undefined) {
      console.log("show message pas d'id valide ou id invalide");
    } else {
      this.UserRegistrationsService.getRegistreredPrestationsByUserId(
        this.userId
      ).subscribe((prestations) => {
        this.prestations = prestations;
        this.showMessageIfListIsEmpty();
      });
    }
  }

  showMessageIfListIsEmpty() {
    if (this.prestations?.length == 0) {
      console.log('show message liste vide');
    }
  }
}
