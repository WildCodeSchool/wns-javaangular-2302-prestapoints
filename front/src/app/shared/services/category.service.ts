import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../model/category';

@Injectable()
export class CategoryService {
  private apiUrl?: string;

  constructor(private http: HttpClient) {}

  getCategories(): Observable<Category[]> {
    this.apiUrl = '/api/categories';

    return this.http.get<Category[]>(this.apiUrl);
  }
}
