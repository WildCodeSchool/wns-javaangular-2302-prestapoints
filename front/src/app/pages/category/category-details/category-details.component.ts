import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Prestation } from 'src/app/shared/model/prestation';
import { CategoryService } from 'src/app/shared/services/category.service';
@Component({
  selector: 'app-category-details',
  templateUrl: './category-details.component.html',
  styleUrls: ['./category-details.component.scss']
})
export class CategoryDetailsComponent implements OnInit {
  prestations: Prestation[] = [];
  selectedCategory: Category = {}; 


  constructor(
    private route: ActivatedRoute,
    private categoryService: CategoryService
  ) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.getPrestationsByCategory(id);
      }
    });

    this.route.queryParams.subscribe(params => {
      if (params && params['state']) {
        this.selectedCategory = params['state'].category;
      }
    });
  }

  

  private getPrestationsByCategory(id: string): void {
    this.categoryService.getPrestationsByCategory(id).subscribe(prestations => {
      this.prestations = prestations;
    });
  }
}
