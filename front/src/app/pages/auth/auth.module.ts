import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing.module';
import { AuthComponent } from './auth.component';

import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AlertComponent } from 'src/app/shared/components/alert/alert.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { LogInComponent } from './log-in/log-in.component';
import { ImageService } from 'src/app/shared/services/image.service';

@NgModule({
  declarations: [AuthComponent, SignInComponent, LogInComponent],
  imports: [CommonModule, AuthRoutingModule, FormsModule, ReactiveFormsModule],
  providers: [ImageService]
})
export class AuthModule {}
