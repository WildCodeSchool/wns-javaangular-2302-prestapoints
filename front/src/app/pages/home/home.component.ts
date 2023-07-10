import { Component, OnInit } from '@angular/core';
import { Prestation } from 'src/app/shared/model/prestation';
import { Image } from 'src/app/shared/model/image';
import { PrestationService } from 'src/app/shared/services/prestation.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  public prestationsApi?: Prestation[];
  
  constructor(private prestationService: PrestationService) {}

  ngOnInit() {
    this.prestationService.getPrestations().subscribe((response) => {

        this.prestationsApi = response;

      console.log( this.prestationsApi);
      
      
    });

    
  }

  

  onGetPrestation(prestationsSearch: Prestation[]): void {
    this.prestationsApi = prestationsSearch;
  }

  
}
