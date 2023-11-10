import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Prestation } from 'src/app/shared/model/prestation';
import { CategoryService } from 'src/app/shared/services/category.service';
import { Category } from 'src/app/shared/model/category';

@Component({
  selector: 'app-category-details',
  templateUrl: './category-details.component.html',
  styleUrls: ['./category-details.component.scss']
})
export class CategoryDetailsComponent implements OnInit {
  prestations: Prestation[] = [];
  selectedCategory: Category = {};
  isMobile: boolean = false; // Ajout de la propriété isMobile

  constructor(
    private route: ActivatedRoute,
    private categoryService: CategoryService,
  ) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.getPrestationsAndCategory(id);
      }
    });

    this.isMobile = window.innerWidth <= 767;
    window.addEventListener('resize', () => {
      this.isMobile = window.innerWidth <= 767;
    });
  }

  private getPrestationsAndCategory(id: string): void {
    this.categoryService.getPrestationsByCategory(id).subscribe(prestations => {
      this.prestations = prestations;
    });

    const staticCategories = this.categoryService.getCategoryStatic();
    const category = staticCategories.find(cat => cat && cat.id && cat.id.toString() === id);
    if (category) {
      this.selectedCategory = category;
    }
  }
}
