import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Injectable } from '@angular/core';
import { Prestation } from '../model/prestation';
import { ResponseApi } from '../model/responseApi';

@Injectable()
export class PrestationService {
  private apiUrl = 'http://localhost:8080/prestations';

  constructor(private http: HttpClient) {}

  getPrestations(): Observable<Prestation[]> {
    return this.http.get<Prestation[]>(this.apiUrl);
  }

  getPrestationById(id: string): Observable<Prestation> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Prestation>(url);
  }

  getPrestationDetails(id: string): Observable<Prestation> {
    const url = `${this.apiUrl}/${id}/details`;
    return this.http.get<Prestation>(url);
  }

  addRegistration(id: number | undefined): Observable<ResponseApi> {
    this.apiUrl = 'http://localhost:8080/prestations/' + id + '/registration';

    return this.http.get<ResponseApi>(this.apiUrl);
  }
}
