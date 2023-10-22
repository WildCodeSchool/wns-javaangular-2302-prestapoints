import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Prestation } from '../shared/model/prestation';
import { HttpClient } from '@angular/common/http';
import { ResponseApi } from '../shared/model/response-api';

@Injectable()
export class PrestationService {
  getPrestationsByCategory(categoryId: string) {
    throw new Error('Method not implemented.');
  }
  private apiUrl?: string;

  constructor(private http: HttpClient) {}

  getPrestations(): Observable<Prestation[]> {
    this.apiUrl =
      'http://localhost:8080/accueil';

    return this.http.get<Prestation[]>(this.apiUrl);
  }

  getPrestationById(id: string): Observable<Prestation> {
    this.apiUrl = 'http://localhost:8080/prestations';
    const url = `${this.apiUrl}/${id}`;

    return this.http.get<Prestation>(url);
  }

  addRegistration(id: number | undefined): Observable<ResponseApi> {
    this.apiUrl = 'http://localhost:8080/prestations/prestation/registration';

    return this.http.post<ResponseApi>(this.apiUrl, id);
  }

  undoRegistration(id: number | undefined): Observable<ResponseApi> {
    this.apiUrl =
      'http://localhost:8080/prestations/prestation/registration/suppression/' +
      id;

    return this.http.delete<ResponseApi>(this.apiUrl);
  }
}
