import { Component, Input, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Prestation } from 'src/app/shared/model/prestation';

@Component({
  selector: 'app-card-details-maxi',
  templateUrl: './card-details-maxi.component.html',
  styleUrls: ['./card-details-maxi.component.scss']
})
export class CardDetailsMaxiComponent implements OnInit {
  @Input() prestation?: Prestation;
  videoUrl: SafeResourceUrl | undefined;

  constructor(private domSanitizer: DomSanitizer) {}

  ngOnInit() {
    const youtubeEmbedUrl = 'https://www.youtube.com/embed/nVoDmaF8Znc';

    this.videoUrl = this.domSanitizer.bypassSecurityTrustResourceUrl(youtubeEmbedUrl);
  }
}
