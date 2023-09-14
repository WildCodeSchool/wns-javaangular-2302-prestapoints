import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminRoutingModule } from './admin-routing.module';
import { UserService } from 'src/app/shared/services/user.service';


@NgModule({
  declarations: [
  ],
  imports: [
    CommonModule,
    AdminRoutingModule
  ],
  providers: [UserService ]
})
export class AdminModule { }
