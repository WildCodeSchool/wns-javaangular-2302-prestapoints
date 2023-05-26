import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Injectable } from '@angular/core';

@Injectable()
export class PrestationService {
  private apiUrl = 'http://localhost:8080/prestations';

  constructor(private http: HttpClient) {}

  getPrestations(): Observable<Prestation> {
    return this.http.get<Prestation>(this.apiUrl);
  }
}
