import { Component, Input, OnInit } from '@angular/core';
import { Prestation } from '../../model/prestation';
import { Router } from '@angular/router';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss'],
})
export class CardComponent {
  @Input() public prestation?: Prestation;

  constructor(private router: Router) {}

  openCardDetails() {
    if (this.prestation && this.prestation.id) {
      this.router.navigate(['/prestations', this.prestation.id, 'details']);
    }
  }  
}
