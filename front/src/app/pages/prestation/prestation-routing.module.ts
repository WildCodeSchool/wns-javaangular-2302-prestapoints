import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PrestationFormulaireComponent } from './prestation-formulaire/prestation-formulaire.component';

const routes: Routes = [
    { path: 'formulaire', component: PrestationFormulaireComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PrestationRoutingModule { }
