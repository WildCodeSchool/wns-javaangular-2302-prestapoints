import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Category } from '../model/category';
import { Prestation } from '../model/prestation';
import { environment } from 'src/app/environments/environments';



@Injectable()
export class CategoryService {

  apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {

  }

  getCategories(): Observable<Category[]> {    
    const url = `${this.apiUrl}/categories`;
    return this.http.get<Category[]>(url);
  }

  getCategoryById(categoryId: string): Observable<Category> {
    const url = `${this.apiUrl}/categories/${categoryId}`;
    return this.http.get<Category>(url);
  }

  getPrestationsByCategory(categoryId: string): Observable<Prestation[]> {
    const url = `${this.apiUrl}/prestations/categories/${categoryId}`; 
    return this.http.get<Prestation[]>(url);
  }

  getStaticCategories(): Observable<Category[]> {
    return of(this.getCategoryStatic());
  }

  getCategoryStatic(): Category[] {
    return [
      {
        name: 'Animaux',
        id: 1,
        img: 'assets/img/animaux.png',
      },
      {
        name: 'Jardinage',
        id: 6,
        img: 'assets/img/jardinage.png',
      },
      {
        name: 'Mode',
        id: 5,
        img: 'assets/img/mode.png',
      },
      {
        name: 'Photographie',
        id: 8,
        img: 'assets/img/photographie.png',
      },
      {
        name: 'Poterie',
        id: 2,
        img: 'assets/img/poterie.png',
      },
      {
        name: 'Restauration',
        id: 3,
        img: 'assets/img/restauration.png',
      },
      {
        name: 'Travaux',
        id: 4,
        img: 'assets/img/travaux.png',
      },
      {
        name: 'Véhicules',
        id: 7,
        img: 'assets/img/vehicules.png',
      },
    ];
  }
}
