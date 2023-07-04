
import { FormGroup, FormControl, Validators, FormBuilder, ValidatorFn, AbstractControl } from '@angular/forms';
import { Component,OnInit } from '@angular/core';
import { Prestation } from '../../../shared/model/Prestation.model';
import { HttpClient } from '@angular/common/http';
import { DateTimeValidatorsService } from '../prestation-service/date-time-handling.service';
import { FormValidatorsService } from '../prestation-service/form-validators.service';


@Component({
  selector: 'app-prestation-formulaire',
  templateUrl: './prestation-formulaire.component.html',
  styleUrls: ['./prestation-formulaire.component.scss']
})
export class PrestationFormulaireComponent {
    
    url : string = "http://localhost:8080/prestations";
    
    prestationForm!: FormGroup;
    dateDuJour: Date= new Date();
    dateDebut: number = 0;
    dateFin: number = 0;
    dateTemp :Date  = new Date(); 
    dateActuelle !: String;
    startTemp: number = 0;
    selectedImageURL!: string;
    selectedImage: File | null = null;
    

    constructor(
        private formBuilder: FormBuilder,
        private http: HttpClient,
        private dateTimeService : DateTimeValidatorsService,
        private formValidatorsService : FormValidatorsService
        ) { }
  
    ngOnInit(): void {

        this.dateActuelle = this.formValidatorsService.DateToDayToString();

        this.prestationForm = this.formBuilder.group({
          title: ['', [Validators.required, Validators.maxLength(50)]],
          duration: ['', [Validators.required, this.formValidatorsService.timeValidator()]],
          dateStart: [this.dateActuelle , [Validators.required]],
          HeureDebutPrestation: ['' , [Validators.required, this.formValidatorsService.timeValidator()]],
          description: ['', [Validators.required, Validators.maxLength(255)]],
          maxUser: ['', Validators.required]
        });
      }
  
    onFileSelected(event: any): void {
        this.selectedImage = event.target.files[0];
        const reader = new FileReader();
      
        reader.onload = (e: any) => {
          this.selectedImageURL = e.target.result;
        };
      
        if (this.selectedImage != null){
            reader.readAsDataURL(this.selectedImage);
        }
        
      }

    
    onSubmit() {
        const formDataAll: FormData = new FormData();

        if (this.prestationForm.invalid) {
          return;
        }

        const formData = this.prestationForm.value;
        
        formData.dateStart = formData.dateStart.replace(/-/g, '/');
        formData.dateStart = this.dateTimeService.formatYyyymmddToDdmmyyyy(formData.dateStart);
        this.dateDebut = this.dateTimeService.convertStrDateTimeToTimestamp(formData.dateStart, formData.HeureDebutPrestation);
        this.dateFin = this.dateDebut + this.dateTimeService.convertTimeToMilliseconds(formData.duration);

        const prestation = new Prestation(
            formData.id,
            formData.title,
            this.dateTimeService.convertTimeToMilliseconds(formData.duration),
            300,            //300 point par defaut
            this.dateDebut,
            this.dateFin,
            "created",
            formData.description,
            formData.maxUser,
        );

        if (this.selectedImage) {
            formDataAll.append('picture', this.selectedImage);
        }

        formDataAll.append('prestation', JSON.stringify(prestation));
        
        //const headers = new HttpHeaders().set('Content-Type', 'multipart/form-data');
    
        this.http.post(this.url, formDataAll).subscribe(
            (response) => {
              console.log("successful");
              // Handle the server response
              this.prestationForm.reset();
              this.selectedImage = null;
            },
            (error) => {
              // Handle errors
              console.error(error);
            }
          );
    }
  }

 