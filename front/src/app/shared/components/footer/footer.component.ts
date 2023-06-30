import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit {
  tags: string[] = [];

  constructor() { }

  ngOnInit() {
    this.tags = this.getTags();
  }

  getTags(): string[] {
    return [
      'Ameublement',
      'Animaux',
      'Artisanat',
      'Bijoux',
      'Bricolage',
      'CréationMeubles',
      'DressageChiens',
      'Poterie',
      'Peinture',
      'Couture',
      'Jardinage',
      'Cuisine',
      'Photographie',
      'Danse',
      'Théâtre',
      'Yoga',
      'Dessin',
      'Musique',
      'Fitness'
    ];
  }
}
