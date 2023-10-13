import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/user';
import { ResponseApi } from '../model/responseApi';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfilService {

  apiUrl: string = "";
  user2: User = new User();

  constructor(public http: HttpClient) { }

  updateUser(user: User): Observable<ResponseApi> {
    this.apiUrl = "/api/update";
    return this.http.post<ResponseApi>(this.apiUrl, user)
  }

  getUserConnected(): Observable<User> {
    return this.http.get<User>("/api/getUserConnected")
  }

  updateAvatar(imageFormData: FormData): Observable<ResponseApi> {
    this.apiUrl = "/api/avatar";
    return this.http.post<ResponseApi>(this.apiUrl, imageFormData)
  }

  getAvatar(): Observable<ArrayBuffer> {
    return this.http.get('/api/get/avatar', { responseType: 'arraybuffer' });
  }
}

