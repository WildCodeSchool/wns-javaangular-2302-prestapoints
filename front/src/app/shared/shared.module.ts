import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FormUserModule } from './components/form-user/form-user.module';


@NgModule({
  declarations: [
  ],
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
})
export class SharedModule {}
