import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, ObservedValueOf } from 'rxjs';
import { User } from 'src/app/shared/model/user';

@Injectable({
  providedIn: 'root'
})
export class SignInService {

  constructor(public http : HttpClient) { }

  createUser(user: User): Observable<User>{
    return this.http.post<User>("http://localhost:8080/public/sign-in", user);
  }

  verifyEmail(email?: string): Observable<boolean>{
    return this.http.post<boolean>("http://localhost:8080/public/email/verification", email);
  }
}
