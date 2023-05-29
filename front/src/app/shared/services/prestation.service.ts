import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Injectable } from '@angular/core';
import { Prestation } from '../model/prestation';

@Injectable()
export class PrestationService {
  private apiUrl?: string;

  constructor(private http: HttpClient) { }

  getPrestations(): Observable<Prestation[]> {
    this.apiUrl = 'http://localhost:8080/prestations';

    return this.http.get<Prestation[]>(this.apiUrl);
  }
}
