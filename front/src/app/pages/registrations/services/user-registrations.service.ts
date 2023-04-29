import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Prestation } from 'src/app/shared/model/prestation';
import { User } from 'src/app/shared/model/user';

@Injectable({
  providedIn: 'root',
})
export class UserRegistrationsService {
  constructor(private http: HttpClient) {}

  getUserById(id: string): Observable<User> {
    return this.http.get<User>('http://localhost:8080/' + id);
  }

  getPrestationsById(id: string): Observable<Prestation> {
    return this.http.get<Prestation>(
      'http://localhost:8080/home/prestation/' + id
    );
  }
  getRegistreredPrestationsByUserId(id: string): Observable<Prestation[]> {
    return this.http.get<Prestation[]>(
      'http://localhost:8080/user/' + id + '/prestations'
    );
  }
}
