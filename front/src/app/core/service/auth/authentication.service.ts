import { Injectable } from '@angular/core';
import { Observable, of } from "rxjs";
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import jwtDecode from 'jwt-decode';

@Injectable()
export class AuthenticationService {

  private authUrl = 'api/auth';

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<boolean> {
    console.log("login => " + username + " " + password);
    return this.http.post<{ token: string }>(this.authUrl, '', {
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + btoa(username + ':' + password),
      }
    })
      .pipe(map(
        response => {
          // login successful if there's a jwt token in the response
          let token = response.token;
          if (token) {
            // store username and jwt token in local storage to keep user logged in between page refreshes
            localStorage.setItem('currentUser', JSON.stringify({ username: username, token: token }));
            // return true to indicate successful login
            console.log("token " + token);
            return true;
          } else {
            // return false to indicate failed login
            return false;
          }
        }));
  }

  getToken(): string {
    // @ts-ignore
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    var token = currentUser && currentUser.token;
    
    return token ? token : "";
  }

  getRoles(): string[] {
    interface JWT {
      role: string[];
      exp: number;
      iat: number;
      sub: string;
    }
    const token = this.getToken();
    
    return (token && jwtDecode<JWT>(token)?.role) || [];
  }

  logout(): void {
    // clear token remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    
  }
}
