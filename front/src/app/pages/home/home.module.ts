import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home.component';
import { PrestationService } from 'src/app/shared/services/prestation.service';
import { SliderComponent } from 'src/app/shared/components/slider/slider.component';
import { CardComponent } from 'src/app/shared/components/card/card.component';
import { SearchbarModule } from 'src/app/shared/components/searchbar/searchbar.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';





@NgModule({
  declarations: [
    HomeComponent,
    SliderComponent,
    CardComponent,
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    SearchbarModule, 
    FormsModule, 
    ReactiveFormsModule
  ], 
  providers: [PrestationService, DatePipe], 
})
export class HomeModule { }
