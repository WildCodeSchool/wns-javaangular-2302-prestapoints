import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { PrestationDto } from 'back/src/main/java/fr/dto/PrestationDto.java';
import { Injectable } from '@angular/core';

@Injectable()
export class PrestationService {
  private apiUrl = 'http://localhost:8080/prestations';

  constructor(private http: HttpClient) {}

  getPrestations(): Observable<PrestationDto[]> {
    return this.http.get<PrestationDto[]>(this.apiUrl);
  }
}

/*import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { User } from 'src/app/shared/model/user';


@Injectable({ providedIn: 'root' })
export class DemoService {
  constructor(private http: HttpClient) {}

  getTestName(): Observable<User> {
    return this.http.get<User>('http://localhost:8080/');
  }
  //https://pokebuildapi.fr/api/v1/pokemon/, { responseType: "json"}
}*/
