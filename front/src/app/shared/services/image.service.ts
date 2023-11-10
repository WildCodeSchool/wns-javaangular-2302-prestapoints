import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Image } from '../model/image';

@Injectable({
    providedIn: 'root'
  })
  export class ImageService {
    private apiUrl = 'http://localhost:8080';
    private baseUrl = '/images'; // L'URL de base de votre API Spring Boot
  
    constructor(private http: HttpClient) { }
  
    // Méthode pour récupérer une image par son ID depuis l'API
    getImageById(imageId: number): Observable<Blob> {
        const url = this.apiUrl + this.baseUrl +  '/' + imageId.toString();
        return this.http.get(url, { responseType: 'blob' });
    }

    // Méthode pour enregistrer une image
    uploadImage(image: File): Observable<number> {
        const url = this.apiUrl + this.baseUrl;
        const formData = new FormData();
        formData.append('image', image);

      return this.http.post<number>(url, formData);
    }

  }