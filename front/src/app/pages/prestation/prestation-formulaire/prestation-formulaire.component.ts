
import { FormGroup, FormControl, Validators, FormBuilder, ValidatorFn, AbstractControl } from '@angular/forms';
import { Component,OnInit } from '@angular/core';
import { Prestation } from '../../../shared/model/Prestation.model';
import { HttpClient } from '@angular/common/http';
import { DateTimeValidatorsService } from '../prestation-service/date-time-validators.service';

@Component({
  selector: 'app-prestation-formulaire',
  templateUrl: './prestation-formulaire.component.html',
  styleUrls: ['./prestation-formulaire.component.scss']
})
export class PrestationFormulaireComponent {
    prestationForm!: FormGroup;
    dateDuJour: Date= new Date();
    dateDebut : Date = new Date();
    dateFin : Date = new Date();
    dateTemp :Date  = new Date(); 
    dateActuelle !: string;

    selectedImage!: File;
    

    constructor(private formBuilder: FormBuilder, private http: HttpClient, private dateTimeService : DateTimeValidatorsService) { }
  
    ngOnInit(): void {
        const aujourdHui = new Date();
        const jour = aujourdHui.getDate();
        const mois = aujourdHui.getMonth() + 1; // Les mois commencent à 0
        const annee = aujourdHui.getFullYear();
        this.dateActuelle = `${jour.toString().padStart(2, '0')}/${mois.toString().padStart(2, '0')}/${annee}`;

        this.prestationForm = this.formBuilder.group({
          title: ['', [Validators.required, Validators.maxLength(50)]],
          duration: ['', [Validators.required, this.dateTimeService.timeValidator()]],
          dateStart: [this.dateActuelle , [Validators.required, this.dateTimeService.dateValidator()]],
          HeureDebutPrestation: ['' , [Validators.required, this.dateTimeService.timeValidator()]],
          description: ['', [Validators.required, Validators.maxLength(255)]],
          maxUser: ['', Validators.required]
        });
      }
  
      

      onFileSelected(event: any): void {
        this.selectedImage = event.target.files[0];
        const reader = new FileReader();
      
        reader.onload = (e: any) => {
          this.selectedImage = e.target.result;
        };
      
        reader.readAsDataURL(this.selectedImage);
      }

    
    onSubmit() {
      if (this.prestationForm.invalid) {
        return;
      }

      const formData = this.prestationForm.value;
      
      this.dateDebut = this.dateTimeService.dateTimeCreator(formData.dateStart, formData.HeureDebutPrestation);
      this.dateFin = this.dateTimeService.dateTimeCreatorWithAddDuration( this.dateTemp, formData.duration);

      const prestation = new Prestation(
        formData.id,
        formData.title,
        formData.duration,
        this.dateDebut,
        this.dateFin,
        formData.state,
        formData.description,
        formData.maxUser,
        [this.selectedImage],
      );

      console.log(prestation);
  
      // Envoie de l'objet JSON au serveur
        this.http.post("http://localhost:8080/prestations", prestation).subscribe(
          response => {
            // Traitement de la réponse du serveur
            this.prestationForm.reset();
          },
          error => {
            // Gestion des erreurs
            console.error(error);
          }
        );
    }
  }

 