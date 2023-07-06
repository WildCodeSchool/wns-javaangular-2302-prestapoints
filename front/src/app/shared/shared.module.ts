import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardComponent } from './components/card/card.component';
import { NavbarComponent } from './components/navbar/navbar.component';

import { ReviewscardComponent } from './components/reviewscard/reviewscard.component';

@NgModule({
  declarations: [NavbarComponent],
  imports: [CommonModule],
})
export class SharedModule {}
