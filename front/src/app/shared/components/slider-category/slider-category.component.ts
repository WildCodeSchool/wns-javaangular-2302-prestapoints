import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-slider-category',
  templateUrl: './slider-category.component.html',
  styleUrls: ['./slider-category.component.scss']
})
export class SliderCategoryComponent implements OnInit {
  constructor() { }

  ngOnInit() {
  }

  categories = [
    {
      title: 'Animaux',
      img: 'assets/img/animaux.png'
    },
    {
      title: 'Jardinage',
      img: 'assets/img/jardinage.png'
    },
    {
      title: 'Mode',
      img: 'assets/img/mode.png'
    },
    {
      title: 'Photographie',
      img: 'assets/img/photographie.png'
    },
    {
      title: 'Poterie',
      img: 'assets/img/poterie.png'
    },
    {
      title: 'Bricolage',
      img: 'assets/img/bricolage.png'
    },
    {
      title: 'Cuisine',
      img: 'assets/img/cuisine.png'
    }
  ];
  
  prevCategory() {
    this.currentIndex =
      this.currentIndex === 0
        ? this.categories.length - 1
        : this.currentIndex - 1;
  }

  nextCategory() {
    this.currentIndex =
      this.currentIndex === this.categories.length - 1
        ? 0
        : this.currentIndex + 1;
  }

  currentIndex: number = 0;
}
