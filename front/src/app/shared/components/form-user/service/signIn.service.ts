import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponseApi } from 'src/app/shared/model/responseApi';
import { User } from 'src/app/shared/model/user';

@Injectable({
  providedIn: 'root'
})
export class SignInService {

  constructor(public http : HttpClient) { }

  createUser(user: User): Observable<ResponseApi>{
    return this.http.post<ResponseApi>("/api/public/sign-in", user);
  }

  verifyEmail(email?: string): Observable<boolean>{
    return this.http.post<boolean>("/api/public/email/verification", email);
  }
}
