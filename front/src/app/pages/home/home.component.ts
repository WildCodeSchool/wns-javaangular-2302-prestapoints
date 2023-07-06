import { Component, OnInit } from '@angular/core';
import { Prestation } from 'src/app/shared/model/prestation';
import { PrestationService } from 'src/app/shared/services/prestation.service';
import { Registration } from 'src/app/shared/model/registration';
//import { RegistrationService } from 'src/app/shared/services/registration.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  public prestationsApi?: Prestation[];
  //public registrationApi?: Registration;

  constructor(
    private prestationService: PrestationService //private registrationService: RegistrationService
  ) {}

  ngOnInit() {
    this.prestationService.getPrestations().subscribe((response) => {
      this.prestationsApi = response;
    });

    /*this.registrationService.getRegistration().subscribe((response) => {
      this.registrationApi = response;
    });*/
  }

  onGetPrestation(prestationsSearch: Prestation[]): void {
    this.prestationsApi = prestationsSearch;
  }

  /*onGetRegistration(registrationSearch: Registration): void {
    this.registrationApi = registrationSearch;
  }*/
}
