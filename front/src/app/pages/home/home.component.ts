import { Component, OnInit } from '@angular/core';
import { Prestation } from 'src/app/shared/model/prestation';
import { PrestationService } from 'src/app/shared/services/prestation.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  public prestations?: Prestation[];
  public prestationTarget?: Prestation;
  public currentIndex = 0;

  constructor(private service: PrestationService) {
    this.prestationTarget = new Prestation('');
  }

  ngOnInit() {
    this.service.getPrestations().subscribe((response) => {
      this.prestations = response;
    });
  }

  prev() {
    if (this.prestations?.length != null) {
      this.currentIndex = 
        this.currentIndex === 0
          ? this.prestations.length - 1
          : this.currentIndex - 1;
    }
  }

  next() {
    if (this.prestations?.length != null) {
      this.currentIndex =
        this.currentIndex === this.prestations.length - 1
          ? 0
          : this.currentIndex + 1;
    }
  }
}
