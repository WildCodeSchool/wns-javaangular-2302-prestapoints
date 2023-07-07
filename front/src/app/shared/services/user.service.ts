import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user';
import { ResponseApi } from '../model/responseApi';
import { SignInService } from 'src/app/pages/auth/sign-in/service/signIn.service';

@Injectable()
export class UserService {
  private apiUrl?: string;

  constructor(private http: HttpClient, private signinService: SignInService) {}

  getUsers(): Observable<User[]> {
    this.apiUrl = 'http://localhost:8080/admin/utilisateurs';

    return this.http.get<User[]>(this.apiUrl);
  }

  saveUser(user: User): Observable<ResponseApi> {
    this.apiUrl =
      'http://localhost:8080/admin/utilisateurs/utilisateur/creation';

    return this.http.post<ResponseApi>(this.apiUrl, user);
  }

  updateUser(user: User): Observable<ResponseApi> {
    this.apiUrl =
      'http://localhost:8080/admin/utilisateurs/utilisateur/modification';

    return this.http.post<ResponseApi>(this.apiUrl, user);
  }

  deleteUser(user: User): Observable<ResponseApi> {
    this.apiUrl =
      'http://localhost:8080/admin/utilisateurs/utilisateur/suppression';

    return this.http.post<ResponseApi>(this.apiUrl, user);
  }

  deleteUsers(users: User[]): Observable<ResponseApi> {
    this.apiUrl =
      'http://localhost:8080/admin/utilisateurs/utilisateur/suppressions';

    return this.http.post<ResponseApi>(this.apiUrl, users);
  }

  public verifyEmail(email: string): Promise<boolean> {
    return new Promise<boolean>((isPresent) => {
      this.signinService.verifyEmail(email).subscribe((emailExists) => {
        isPresent(emailExists);
      });
    });
  }
}
