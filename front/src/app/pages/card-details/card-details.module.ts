import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CardDetailsRoutingModule } from './card-details-routing.module';
import { CardDetailsComponent } from './card-details.component';
import { CardDetailsMaxiComponent } from 'src/app/shared/components/card-details-maxi/card-details-maxi.component';
import { CardDetailsMiniComponent } from 'src/app/shared/components/card-details-mini/card-details-mini.component';


@NgModule({
  declarations: [
    CardDetailsComponent,
    CardDetailsMaxiComponent,
    CardDetailsMiniComponent
  ],
  imports: [
    CommonModule,
    CardDetailsRoutingModule
  ]
})
export class CardDetailsModule { }
