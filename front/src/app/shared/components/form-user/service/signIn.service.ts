import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/app/environments/environment';
import { ResponseApi } from 'src/app/shared/model/responseApi';
import { User } from 'src/app/shared/model/user';

@Injectable({
  providedIn: 'root',
})
export class SignInService {
  private apiUrl?: string;
  environmentUrl = environment.apiUrl;

  constructor(public http: HttpClient) {}

  createUser(user: User): Observable<ResponseApi> {
    this.apiUrl = `${this.environmentUrl}/public/sign-in`;

    return this.http.post<ResponseApi>(this.apiUrl, user);
  }

  verifyEmail(email?: string): Observable<boolean> {
    this.apiUrl = `${this.environmentUrl}/public/email/verification`;

    return this.http.post<boolean>(this.apiUrl, email);
  }
}
