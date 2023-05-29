import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home.component';
import { CardComponent } from 'src/app/shared/components/card/card.component';
import { PrestationService } from 'src/app/shared/services/prestation.service';
import { SearchbarComponent } from 'src/app/shared/searchbar/searchbar.component';
import { SliderComponent } from 'src/app/shared/components/slider/slider.component';


@NgModule({
  declarations: [
    HomeComponent,
    CardComponent,
    SearchbarComponent,
    SliderComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule
  ], 
  providers: [PrestationService],
})
export class HomeModule { }
