import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePage } from './home.page';

const routes: Routes = [
  { path: '', component: HomePage },
  // { path: 'auth', loadChildren: () => import('./pages/auth/auth.module').then(m => m.AuthModule) },
  // { path: 'prestation', loadChildren: () => import('./pages/prestation/prestation.module').then(m => m.PrestationModule) },
  // { path: 'prestations/:id/details', loadChildren: () => import('./pages/card-details/card-details.module').then(m => m.CardDetailsModule) },
  // { path: 'categorie/:id/prestations', loadChildren: () => import('./pages/category/category-details/category-details.module').then(m => m.CategoryDetailsModule) },
  // { path: 'reservation', loadChildren: () => import('./pages/home/home.module').then(m => m.HomeModule) },
  // { path: 'admin', loadChildren: () => import('./pages/admin/admin.module').then(m => m.AdminModule) }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HomePageRoutingModule {}
