import { HttpClient, HttpResponseBase } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Injectable } from '@angular/core';
import { Prestation } from '../model/prestation';
import { ResponseApi } from '../model/responseApi';

@Injectable()
export class PrestationService {
  private apiUrl?: string;

  constructor(private http: HttpClient) {}

  getPrestations(): Observable<Prestation[]> {
    this.apiUrl = '/api/accueil';

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
