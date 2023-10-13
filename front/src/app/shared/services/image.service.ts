import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
  })
  export class ImageService {
    private apiUrl = '/api';
    private baseUrl = '/images'; // L'URL de base de votre API Spring Boot
  
    constructor(private http: HttpClient) { }
  
    // Méthode pour récupérer une image par son ID depuis l'API
    getImageById(imageId: number): Observable<Blob> {
      const url = this.apiUrl + this.baseUrl +  '/' + imageId.toString();
      return this.http.get(url, { responseType: 'blob' });
    }
  }