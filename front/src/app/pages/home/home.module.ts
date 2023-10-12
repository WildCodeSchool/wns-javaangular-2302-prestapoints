import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home.component';
import { PrestationService } from 'src/app/shared/services/prestation.service';
import { SliderComponent } from 'src/app/shared/components/slider/slider.component';
import { CardComponent } from 'src/app/shared/components/card/card.component';
import { SearchbarModule } from 'src/app/shared/components/searchbar/searchbar.module';
import { ReservationComponent } from 'src/app/shared/components/reservation/reservation.component';
import { ReviewscardComponent } from 'src/app/shared/components/reviewscard/reviewscard.component';

@NgModule({
  declarations: [
    HomeComponent,
    SliderComponent,
    CardComponent,
    ReservationComponent //à suppr en attente de Luc,
    ReviewscardComponent,
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
export class HomeModule {}
