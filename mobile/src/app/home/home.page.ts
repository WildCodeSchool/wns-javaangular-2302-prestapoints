import { Component, OnInit } from '@angular/core';
import { PrestationService } from '../services/prestation.service';
import { Prestation } from '../shared/model/prestation';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage implements OnInit {
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
      console.log(this.prestationsApi);
    });
  }

  onGetPrestation(prestationsSearch: Prestation[]): void {
    this.prestationsApi = prestationsSearch;
  }
}

