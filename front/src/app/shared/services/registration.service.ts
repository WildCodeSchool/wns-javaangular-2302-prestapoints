import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
/*import { Injectable } from '@angular/core';
import { Registration } from '../model/registration';

@Injectable()
export class RegistrationService {
  private apiUrl2?: string;

  constructor(private http: HttpClient) {}

  getRegistration(): Observable<Registration> {
    this.apiUrl2 = 'http://localhost:8080/registration';
    //apiUrl apparait deux fois donc je dois le renommer ?
    return this.http.get<Registration>(this.apiUrl2);
  }

  sendRating(evaluation: number): Observable<any> {
    this.apiUrl2 = 'http://localhost:8080/evaluation';

    // Utilisez cette méthode pour effectuer une requête HTTP POST vers votre API backend
    return this.http.post(this.apiUrl2, { evaluation });
  }
}*/
