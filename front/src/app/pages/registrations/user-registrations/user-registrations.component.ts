import { Component } from '@angular/core';
import { User } from 'src/app/shared/model/user';
import { UserRegistrationsService } from '../services/user-registrations.service';
import { Prestation } from 'src/app/shared/model/prestation';

@Component({
  selector: 'app-user-registrations',
  templateUrl: './user-registrations.component.html',
  styleUrls: ['./user-registrations.component.scss'],
})
export class UserRegistrationsComponent {
  public prestations?: Prestation[];

  constructor(public UserRegistrationsService: UserRegistrationsService) {
    this.prestations = [];
  }

  ngOnInit() {}

  getRegistreredPrestationsByUserId(id: string): void {
    this.UserRegistrationsService.getRegistreredPrestationsByUserId(
      id
    ).subscribe((prestations) => {
      this.prestations = prestations;
    });
  }
}
