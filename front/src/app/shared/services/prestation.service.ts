import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Injectable } from '@angular/core';
import { Prestation } from '../model/prestation';
import { ResponseApi } from '../model/responseApi';

@Injectable()
export class PrestationService {
  private apiUrl?: string;

  constructor(private http: HttpClient) { }

  getPrestations(): Observable<Prestation[]> {
    this.apiUrl = 'http://localhost:8080/prestations';

    return this.http.get<Prestation[]>(this.apiUrl);
  }

  addRegistration( id: number | undefined): Observable<ResponseApi> {
    this.apiUrl = 'http://localhost:8080/prestations/' + id + '/registration';
    console.log("in service go to url .." + this.apiUrl)
    return this.http.get<ResponseApi>(this.apiUrl);
  }
}
