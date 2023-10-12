import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-card-category',
  templateUrl: './card-category.component.html',
  styleUrls: ['./card-category.component.scss']
})
export class CardCategoryComponent implements OnInit {

  @Input() category: any;

  constructor() { }

  ngOnInit() {
  }

}
