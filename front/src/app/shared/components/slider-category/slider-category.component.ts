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
      id: '1',
      img: 'assets/img/animaux.png'
    },
    {
      title: 'Jardinage',
      id: '6',
      img: 'assets/img/jardinage.png'
    },
    {
      title: 'Mode',
      id: '5',
      img: 'assets/img/mode.png'
    },
    {
      title: 'Photographie',
      id: '8',
      img: 'assets/img/photographie.png'
    },
    {
      title: 'Poterie',
      id: '2',
      img: 'assets/img/poterie.png'
    },
    {
      title: 'Bricolage',
      id: '4',
      img: 'assets/img/bricolage.png'
    },
    {
      title: 'Cuisine',
      id: '7',
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
