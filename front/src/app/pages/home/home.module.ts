import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home.component';
import { PrestationService } from 'src/app/shared/services/prestation.service';
import { SearchbarComponent } from 'src/app/shared/searchbar/searchbar.component';
import { SliderComponent } from 'src/app/shared/components/slider/slider.component';
import { CardComponent } from 'src/app/shared/components/card/card.component';


@NgModule({
  declarations: [
    HomeComponent,
    SearchbarComponent,
    SliderComponent,
    CardComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule
  ], 
  providers: [PrestationService],
})
export class HomeModule { }
