import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CardDetailsRoutingModule } from './card-details-routing.module';
import { CardDetailsComponent } from './card-details.component';
import { CardDetailsMaxiComponent } from 'src/app/shared/components/card-details-maxi/card-details-maxi.component';
import { CardDetailsMiniComponent } from 'src/app/shared/components/card-details-mini/card-details-mini.component';

import { PrestationService } from 'src/app/shared/services/prestation.service'; 
import { FormatDurationPipe } from 'src/app/shared/pipes/format-duration.pipe';

@NgModule({
  declarations: [
    CardDetailsComponent,
    CardDetailsMaxiComponent,
    CardDetailsMiniComponent,
    FormatDurationPipe
  ],
  imports: [
    CommonModule,
    CardDetailsRoutingModule,
  ],
  providers: [
    PrestationService 
  ]
})
export class CardDetailsModule { }