import { Component, OnInit } from '@angular/core';
import { Prestation } from 'src/app/shared/model/prestation';
import { Image } from 'src/app/shared/model/image';
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

  constructor(private prestationService: PrestationService) {}

  ngOnInit() {
    this.getPrestations();
  }

  needToRefresh($event: boolean) {
    if ($event) {
      this.getPrestations();
    }
  }

  getPrestations() {
    this.prestationService.getPrestations().subscribe((response) => {
      this.prestationsApi = response;
    });
  }

  onGetPrestation(prestationsSearch: Prestation[]): void {
    this.prestationsApi = prestationsSearch;
  }
}
