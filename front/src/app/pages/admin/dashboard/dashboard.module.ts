import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard.component';
import { TableComponent } from '../components/table/table.component';
import { UserformModule } from '../components/userform/userform.module';
import { ToolsService } from 'src/app/shared/services/tools.service';
import { SearchModule } from '../components/search/search.module';

@NgModule({
  declarations: [
    DashboardComponent, TableComponent
  ],
  imports: [
    CommonModule, UserformModule, SearchModule
  ],
  providers: [ToolsService]
})
export class DashboardModule { }
