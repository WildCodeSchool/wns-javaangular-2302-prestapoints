import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user';
import { ResponseApi } from '../model/responseApi';

@Injectable()
export class UserService {
  private apiUrl?: string;

  constructor(private http: HttpClient) {}

  getUsers(): Observable<User[]> {
    this.apiUrl = 'http://localhost:8080/admin/utilisateurs';

    return this.http.get<User[]>(this.apiUrl);
  }

  deleteUser(user: User): Observable<ResponseApi> {
    this.apiUrl = 'http://localhost:8080/admin/utilisateurs/utilisateur/suppression';

    return this.http.post<ResponseApi>(this.apiUrl, user);
  }
}
