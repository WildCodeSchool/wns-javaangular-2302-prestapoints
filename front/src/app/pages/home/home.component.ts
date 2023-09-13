import { Component, OnInit } from '@angular/core';
import { Prestation } from 'src/app/shared/model/prestation';
import { Image } from 'src/app/shared/model/image';
import { PrestationService } from 'src/app/shared/services/prestation.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  public prestationsApi?: Prestation[];

  constructor(private prestationService: PrestationService, private router: Router) {}

  ngOnInit() {
    this.prestationService.getPrestations().subscribe((response) => {

        this.prestationsApi = response;

      console.log( this.prestationsApi);
      
      
    });

    
  }

  

  onGetPrestation(prestationsSearch: Prestation[]): void {
    this.prestationsApi = prestationsSearch;
  }

  

  redirectToCardDetails(prestation: Prestation) {
    const prestationId = prestation.id; 
    this.router.navigate(['prestations', prestationId, 'details']);
  }
}
