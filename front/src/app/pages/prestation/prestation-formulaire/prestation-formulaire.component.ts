
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Component,OnInit } from '@angular/core';
import { Prestation } from '../model/Prestation.model';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-prestation-formulaire',
  templateUrl: './prestation-formulaire.component.html',
  styleUrls: ['./prestation-formulaire.component.scss']
})
export class PrestationFormulaireComponent {
    prestationForm!: FormGroup;

    constructor(private formBuilder: FormBuilder, private http: HttpClient) { }
  
    ngOnInit(): void {
        this.prestationForm = this.formBuilder.group({
          title: ['', Validators.required],
          duration: ['', Validators.required],
          addPoint: ['', Validators.required],
          dateStart: ['', Validators.required],
          dateEnd: ['', Validators.required],
          state: ['', Validators.required],
          description: ['', Validators.required],
          maxUser: ['', Validators.required]
        });
      }
  
    onSubmit() {
      if (this.prestationForm.invalid) {
        return;
      }
  
      const formData = this.prestationForm.value;
      const prestation = new Prestation(
        formData.title,
        formData.duration,
        formData.addPoint,
        formData.dateStart,
        formData.dateEnd,
        formData.state,
        formData.description,
        formData.maxUser
      );
  
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