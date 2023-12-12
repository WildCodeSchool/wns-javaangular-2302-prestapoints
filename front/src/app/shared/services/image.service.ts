import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/app/environments/environment';

@Injectable({
    providedIn: 'root'
  })
  export class ImageService {

    private environnementURL = environment.apiUrl;

    private baseUrl = '/images'; 
  
    constructor(private http: HttpClient) { }
  
    // Méthode pour récupérer une image par son ID depuis l'API
    getImageById(imageId: number): Observable<Blob> {
      const url = this.environnementURL + this.baseUrl +  '/' + imageId.toString();
      return this.http.get(url, { responseType: 'blob' });
    }
  }