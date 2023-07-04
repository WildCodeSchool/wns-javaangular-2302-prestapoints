import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PrestationRoutingModule } from './prestation-routing.module';
import { PrestationFormulaireComponent } from './prestation-formulaire/prestation-formulaire.component';
import { FormBuilder, FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { DateTimeValidatorsService } from './prestation-service/date-time-handling.service';
import { FormValidatorsService } from './prestation-service/form-validators.service';

@NgModule({
  declarations: [
    PrestationFormulaireComponent
  ],
  imports: [
    CommonModule,
    PrestationRoutingModule,
    FormsModule,ReactiveFormsModule,
  ],providers: [
    DateTimeValidatorsService, 
    FormValidatorsService]
})
export class PrestationModule { }
