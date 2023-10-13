import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Prestation } from 'src/app/shared/model/prestation';
import { CategoryService } from 'src/app/shared/services/category.service';

@Component({
  selector: 'app-category-details',
  templateUrl: './category-details.component.html',
  styleUrls: ['./category-details.component.css']
})
export class CategoryDetailsComponent implements OnInit {
  prestations: Prestation[] = [];
  selectedCategory: any;

  constructor(
    private route: ActivatedRoute,
    private categoryService: CategoryService
  ) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.getPrestationsByCategory(id);
        this.getCategoryDetails(id); 
      }
    });
  }

  getPrestationsByCategory(id: string): void {
    this.categoryService.getPrestationsByCategory(id).subscribe(prestations => {
      this.prestations = prestations;
    });
  }

  getCategoryDetails(id: string): void {
    this.categoryService.getCategoryById(id).subscribe(category => {
      this.selectedCategory = category;
    });
  }
}
