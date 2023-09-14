import { Component, Input, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Prestation } from 'src/app/shared/model/prestation';
import { PrestationService } from 'src/app/shared/services/prestation.service';
import { HttpClient } from '@angular/common/http';
import { CardDetailsModule } from 'src/app/pages/card-details/card-details.module';
import { Location } from '../../model/location';
import { Category } from '../../model/category';

@Component({
  selector: 'app-card-details-maxi',
  templateUrl: './card-details-maxi.component.html',
  styleUrls: ['./card-details-maxi.component.scss']
})
export class CardDetailsMaxiComponent implements OnInit {
  @Input() prestation?: Prestation;
  tags: string[] = [];
  cardVideo: Prestation[] = [];
  commentaires: any[] = [];
  category: Category = new Category();
  videoUrl: SafeResourceUrl | undefined;
  location: Location = new Location();
  duration: string = '';
  description: string = '';
  littleDescription: string = '';
  practicalInformation: string = '';
  language: string = '';
  personalInfos: string = '';
  locationInfos: string = '';

  constructor(
    private domSanitizer: DomSanitizer,
    private prestationService: PrestationService,
    private http: HttpClient
  ) {}

  ngOnInit() {
    //this.tags = this.getTags();
    //if (this.prestation && this.prestation.id) {
    //  this.getPrestationDetails();
    //  this.getVideos();
    //  this.getCommentaires();
    //}
  }
   
  //public getTags(): string[] {
  //  return this.prestation?.tags ?? [];
  //}

  //public getPrestationDetails(): void {
  //  if (this.prestation && this.prestation.id) {
  //    this.prestationService.getPrestationById(this.prestation.id)
  //      .subscribe((response: Prestation) => {
  //        if (response.category) {
  //          this.category = response.category;
  //        }
  //        if (response.location) {
  //          this.location = response.location;
  //        }
  //        this.duration = response.duration ?? '';
  //        this.description = response.description ?? '';
  //        this.littleDescription = response.littleDescription ?? '';
  //        this.practicalInformation = response.practicalInformation ?? '';
  //        this.language = response.language ?? '';
  //        this.personalInfos = response.personalInfos ?? '';
  //        this.locationInfos = response.locationInfos ?? '';
  //      });
  //  }
  //}

  public getVideos(): void {
    if (this.prestation && this.prestation.id) {
      this.http.get<any>(`/api/prestations/${this.prestation.id}/videos`)
        .subscribe((response) => {
          this.cardVideo = response;
        });
    }
  }

  public getCommentaires(): void {
    if (this.prestation && this.prestation.id) {
      this.http.get<any>(`/api/prestations/${this.prestation.id}/commentaires`)
        .subscribe((response) => {
          this.commentaires = response;
        });
    }
  }
}
