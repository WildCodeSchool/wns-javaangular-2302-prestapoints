import { Component, OnInit } from '@angular/core';
import { Category } from '../../model/category';
import { CategoryService } from '../../../services/category.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss'],
})
export class FooterComponent implements OnInit {
  public tags?: Category[];

  constructor(private categoryService: CategoryService) {}

  ngOnInit() {
    this.tagsSubscription();
  }

  tagsSubscription() {
    this.categoryService.getCategories().subscribe((categories) => {
      this.tags = categories;
    });
  }
}