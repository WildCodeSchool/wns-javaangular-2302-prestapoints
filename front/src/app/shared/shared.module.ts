import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CardComponent } from './components/card/card.component';

@NgModule({
  declarations: [CardComponent],
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  exports: [CardComponent],
})
export class SharedModule {}
