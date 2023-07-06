import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { Prestation } from 'src/app/shared/model/prestation';

class Commentaire {
  date!: string;
  texte!: string;
}

@Component({
  selector: 'app-card-details-maxi',
  templateUrl: './card-details-maxi.component.html',
  styleUrls: ['./card-details-maxi.component.scss']
})
export class CardDetailsMaxiComponent implements OnInit {
  @Input() prestation?: Prestation;
  
  tags: string[] = [];
  cardVideo: Prestation[] = [];
  commentaires: Commentaire[] = []; 

  constructor(private domSanitizer: DomSanitizer) { }

  ngOnInit() {
    this.tags = this.getTags();
    this.getVideos();
    this.getCommentaires(); 
  }

  private getVideos() {
    const video = new Prestation();
    video.url = this.getSanitizedURL("https://www.youtube.com/embed/gov2PW3i-9Q");
    video.title = "En deux tours de mains | Soazig Hamon - Tapissier d'ameublement";

    this.cardVideo = [video];
  }

  private getSanitizedURL(url: string): SafeUrl {
    return this.domSanitizer.bypassSecurityTrustResourceUrl(url);
  }

  public getTags(): string[] {
    return [
      'Ameublement',
      'Animaux',
      'Artisanat',
      'Bijoux',
      'Bricolage',
      'CréationMeubles',
      'DressageChiens',
      'Poterie',
      'Peinture',
      'Couture',
      'Jardinage',
      'Cuisine',
      'Photographie',
      'Danse',
      'Théâtre',
      'Yoga',
      'Dessin',
      'Musique',
      'Fitness'
    ];
  }

  private getCommentaires() {
    const commentaire1 = new Commentaire();
    commentaire1.date = 'Le 25/02/2023';
    commentaire1.texte = "Excellent moment passé avec Soazig qui, outre être sympathique et accueillante, a l'art de transmettre son savoir-faire avec pédagogie et gentillesse. L'endroit où elle dispense son savoir est spacieux et chaleureux et Soazig y met à la disposition de ses élèves tous les outils et petites fournitures nécessaires.";

    const commentaire2 = new Commentaire();
    commentaire2.date = 'Le 29/01/2023';
    commentaire2.texte = "1er atelier avec Soazig et c'était une très chouette rencontre ; bonne communication avant pour préparer l'atelier, et même si je n'ai pas pu concrétiser mon projet initial car trop ambitieux, Soazig a pu m'accompagner sur un autre projet en expliquant très bien les différentes étapes. Certainement à refaire !";
    
    const commentaire3 = new Commentaire();
    commentaire3.date = 'Le 12/03/2023';
    commentaire3.texte = "J'ai participé à l'atelier de Soazig récemment et j'ai été impressionné par son expertise et sa passion. Elle a une grande attention aux détails et m'a guidé tout au long du processus. J'ai appris beaucoup de nouvelles techniques et je suis reparti avec un magnifique projet terminé. Je recommande vivement ses ateliers à tous les amateurs d'artisanat!";


    this.commentaires = [commentaire1, commentaire2, commentaire3]
  }
}
