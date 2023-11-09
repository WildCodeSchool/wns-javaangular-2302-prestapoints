import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CategoryDetailsComponent } from './category-details.component';
import { SharedModule } from 'src/app/shared/shared.module';

@NgModule({
  imports: [CommonModule, SharedModule],
  declarations: [CategoryDetailsComponent],
  exports: [CategoryDetailsComponent],
})
export class CategoryDetailsModule {}

