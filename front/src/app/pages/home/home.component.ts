import { Component, OnInit } from '@angular/core';
import { Prestation } from 'src/app/shared/model/prestation';
import { PrestationService } from 'src/app/shared/services/prestation.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  public prestationsApi?: Prestation[];
  
  constructor(private service: PrestationService) {}

  ngOnInit() {
    this.service.getPrestations().subscribe((response) => {
      this.prestationsApi = response;
    });
  }
}