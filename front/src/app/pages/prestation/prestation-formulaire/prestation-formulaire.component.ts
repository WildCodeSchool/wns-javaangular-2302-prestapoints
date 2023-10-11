
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Component,OnInit } from '@angular/core';
import { Prestation } from '../../../shared/model/prestation';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-prestation-formulaire',
  templateUrl: './prestation-formulaire.component.html',
  styleUrls: ['./prestation-formulaire.component.scss']
})
export class PrestationFormulaireComponent {
    prestationFormBasic!: FormGroup;
    prestationFormDescription!: FormGroup;
    prestationFormLocation!: FormGroup;
    prestationFormMedia!: FormGroup;
    constructor(private formBuilder: FormBuilder, private http: HttpClient) { }
  
    ngOnInit(): void {

        this.prestationFormBasic = this.formBuilder.group({
          title: ['', Validators.required],
          type: ['', Validators.required],
          category: ['', Validators.required],
          duration: ['', Validators.required],
          addPoint: ['', Validators.required],
          dateStart: ['', Validators.required],
          //dateEnd: ['', Validators.required],
          maxUser: ['', Validators.required]
        });

        this.prestationFormDescription = this.formBuilder.group({
            practicalInformation: ['', Validators.required],
            description: ['', Validators.required],
            littleDescription: ['', Validators.required],
            language: ['', Validators.required],
            personalInfos: ['', Validators.required],
        });

        this.prestationFormLocation = this.formBuilder.group({
            LocationCity: ['', Validators.required],
            LocationPostalCode: ['', Validators.required],
            LocationAddress: ['', Validators.required],
            LocationAddressNumber: ['', Validators.required],
            LocationAddressInformation: ['', Validators.required],
        });

        this.prestationFormMedia= this.formBuilder.group({
            mediaImage: ['', Validators.required],
            mediaVideo: ['', Validators.required],
        });
      }
  
    onSubmit() {
      if (this.prestationFormBasic.invalid) {
        return;
      }
  
      const formDataBasic = this.prestationFormBasic.value;
      const prestation = new Prestation(
        //formData.title,
        //formData.duration,
        //formData.addPoint,
        //formData.dateStart,
        //formData.dateEnd,
        //formData.state,
        //formData.description,
        //formData.maxUser
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