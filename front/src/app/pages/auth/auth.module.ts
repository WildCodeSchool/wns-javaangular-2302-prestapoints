import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing.module';
import { AuthComponent } from './auth.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { LogInComponent } from './log-in/log-in.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AlertComponent } from 'src/app/shared/alert/alert.component';


@NgModule({
  declarations: [
    AuthComponent,
    SignInComponent,
    LogInComponent, 
    AlertComponent
  ],
  imports: [
    CommonModule,
    AuthRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class AuthModule { }
