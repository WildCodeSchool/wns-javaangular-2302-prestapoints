import { Component, Input, OnInit } from '@angular/core';
import { User } from 'src/app/shared/model/user';


@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent {
  @Input()
  public users?: User[];
  
  constructor(){}

}
