import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RegistrationsRoutingModule } from './registrations-routing.module';
import { RegistrationsComponent } from './registrations.component';
import { UserRegistrationsComponent } from './user-registrations/user-registrations.component';

@NgModule({
  declarations: [RegistrationsComponent, UserRegistrationsComponent],
  imports: [CommonModule, RegistrationsRoutingModule],
})
export class RegistrationsModule {}
