import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardComponent } from './components/card/card.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ProfilComponent } from './components/profil/profil.component';

@NgModule({
  declarations: [
    NavbarComponent,
  ],
  imports: [CommonModule],
})
export class SharedModule {}
