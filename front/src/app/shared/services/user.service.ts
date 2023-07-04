import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user';

@Injectable()
export class UserService {
  private apiUrl?: string;

  constructor(private http: HttpClient) {}

  getUsers(): Observable<User[]> {
    // console.log("in getUsers")
    this.apiUrl = 'http://localhost:8080/admin/utilisateurs';
    // console.log("url " + this.apiUrl)
    return this.http.get<User[]>(this.apiUrl);
  }
}
