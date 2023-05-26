import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PrestationDto } from 'back/src/main/java/fr/dto/PrestationDto.java';
import { Injectable } from '@angular/core';

@Injectable()
export class PrestationService {
  private apiUrl = 'http://localhost:8080/prestations';

  constructor(private http: HttpClient) {}

  getPrestations(): Observable<PrestationDto[]> {
    return this.http.get<PrestationDto[]>(this.apiUrl);
  }
}
