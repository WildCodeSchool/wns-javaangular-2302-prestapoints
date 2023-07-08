import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard.component';
import { TableComponent } from '../components/table/table.component';
import { UserformModule } from '../components/userform/userform.module';
import { ToolsService } from 'src/app/shared/services/tools.service';



@NgModule({
  declarations: [
    DashboardComponent, TableComponent,
  ],
  imports: [
    CommonModule, UserformModule
  ],
  providers: [ToolsService]
})
export class DashboardModule { }
