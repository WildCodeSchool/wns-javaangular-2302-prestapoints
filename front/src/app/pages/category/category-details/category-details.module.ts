import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CategoryDetailsComponent } from './category-details.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { CategoryRoutingModule } from './category-routing.module';
import { SliderComponent } from 'src/app/shared/components/slider/slider.component';

@NgModule({
  imports: [
    CommonModule, 
    SharedModule,
    CategoryRoutingModule
  ],
  declarations: [
    CategoryDetailsComponent
  ],
  exports: [CategoryDetailsComponent],
})
export class CategoryDetailsModule {}

