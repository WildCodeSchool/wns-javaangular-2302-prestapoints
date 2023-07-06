import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Injectable } from '@angular/core';

import { ResponseApi } from '../model/responseApi';
import { Prestation } from '../model/Prestation';

@Injectable()
export class PrestationService {
  private apiUrl?: string;

  constructor(private http: HttpClient) {}

  getPrestations(): Observable<Prestation[]> {
    this.apiUrl = 'http://localhost:8080/prestations';

    return this.http.get<Prestation[]>(this.apiUrl);
  }

  addRegistration(id: number | undefined): Observable<ResponseApi> {
    this.apiUrl = 'http://localhost:8080/prestations/' + id + '/registration';

    return this.http.get<ResponseApi>(this.apiUrl);
  }
}
