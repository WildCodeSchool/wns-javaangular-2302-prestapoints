import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Injectable } from '@angular/core';
import { Prestation } from '../model/prestation';

@Injectable()
export class PrestationService {
  private apiUrl: string = 'http://localhost:8080/prestations';

  constructor(private http: HttpClient) {}

  getPrestations(): Observable<Prestation[]> {
    return this.http.get<Prestation[]>(this.apiUrl);
  }
}