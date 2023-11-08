import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Prestation } from 'src/app/shared/model/prestation';
import { CategoryService } from 'src/app/shared/services/category.service';
import { Category } from 'src/app/shared/model/category';

@Component({
  selector: 'app-category-details',
  templateUrl: './category-details.component.html',
  styleUrls: ['./category-details.component.css']
})
export class CategoryDetailsComponent implements OnInit {
  prestations: Prestation[] = []; 
  categories: Category[] = [];
  selectedCategory: any;

  constructor(
    private route: ActivatedRoute,
    private categoryService: CategoryService,
  ) {
    console.log('CategoryDetailsComponent loaded'); // Ajoutez cette ligne
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      const id = params['id'];
      console.log('Category ID:', id); // Ajoutez cette ligne
      if (id) {
        this.getPrestationsByCategory(id);
      }
    });
  }

  getPrestationsByCategory(id: string): void {
    this.categoryService.getPrestationsByCategory(id).subscribe(prestations => {
      console.log('Prestations:', prestations);
      this.prestations = prestations;
    });
  }
}
