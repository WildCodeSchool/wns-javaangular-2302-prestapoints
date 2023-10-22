import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Prestation } from 'src/app/shared/model/prestation';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss'],
})
export class CardComponent {
  @Input() public prestation?: Prestation;
  public imageBlob: Blob | undefined;
  public imageUrl: string | undefined;

  
  constructor(private router: Router,
    // private imageService: ImageService
  ) { }

  ngOnInit(): void {
      // on recupere l'image en demandant a l'api l'image que l'on veux
      // if(this.prestation?.images[0].id != undefined ){
      //     this.imageService.getImageById(this.prestation?.images[0].id).subscribe(
      //         (data: Blob) => {
      //             this.imageBlob = data;
      //             this.imageUrl = URL.createObjectURL(data); // Convertir le Blob en URL d'image
      //         },
      //         error => {
      //             console.error('Erreur lors de la récupération de l\'image', error);
      //         }
      //     );
      // }  
  }

  openCardDetails() {
      if (this.prestation && this.prestation.id) {
      this.router.navigate(['/prestations', this.prestation.id, 'details']);
      }
  } 
  
}

