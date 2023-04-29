import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'user/:id/registrations',
    loadChildren: () =>
      import('./pages/registrations/registrations.module').then(
        (m) => m.RegistrationsModule
      ),
  },
];

@NgModule({
  imports: [BrowserModule, RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
