import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, delay, map } from 'rxjs';

@Injectable()
export class ImageService {
  constructor(private http: HttpClient) {}

  uploadAvatar(avatarFile: File): Observable<any> {
    const formData = new FormData();
    formData.append('file', avatarFile);

    return this.http.post('http://localhost:8080/avatars/upload', formData);
  }

  getAvatar(avatarId: number): Observable<string> {
    return new Observable<string>((observer) => {
      this.http
        .get('http://localhost:8080/avatars/' + avatarId, {
          responseType: 'blob',
        })
        .subscribe((avatarBlob: Blob) => {
          const reader = new FileReader();
          reader.readAsDataURL(avatarBlob);
          reader.onloadend = () => {
            const avatarUrl = reader.result as string;
            observer.next(avatarUrl);
            observer.complete();
          };
        });
    });
  }
}
