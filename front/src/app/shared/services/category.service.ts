import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../model/category';
import { Prestation } from '../model/prestation';

@Injectable()
export class CategoryService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  getCategories(): Observable<Category[]> {
    this.apiUrl = 'http://localhost:8080/categories';
    return this.http.get<Category[]>(this.apiUrl);
  }

  getCategoryById(categoryId: string): Observable<Category> {
    const url = `${this.apiUrl}/categories/${categoryId}`;
    return this.http.get<Category>(url);
  }

  getPrestationsByCategory(categoryId: string): Observable<Prestation[]> {
    this.apiUrl = 'http://localhost:8080/prestations/categories/id ';
    const url = `${this.apiUrl}/${categoryId}/prestations`;
    return this.http.get<Prestation[]>(url);
  }
}
