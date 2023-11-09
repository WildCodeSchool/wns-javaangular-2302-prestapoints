import { Component, OnInit } from '@angular/core';
import { Category } from '../../model/category';
import { CategoryService } from '../../services/category.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-slider-category',
  templateUrl: './slider-category.component.html',
  styleUrls: ['./slider-category.component.scss']
})
export class SliderCategoryComponent implements OnInit {

  categories : Category[] = [];

  constructor(private router: Router, private categoryService: CategoryService) { }

  ngOnInit() {
    this.categoryService.getStaticCategories().subscribe(categories => {
      this.categories = categories;
    });
  }
  
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
