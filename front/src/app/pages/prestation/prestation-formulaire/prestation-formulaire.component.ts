
import { FormGroup, FormControl, Validators, FormBuilder, ValidatorFn, AbstractControl } from '@angular/forms';
import { Component,OnInit } from '@angular/core';
import { Prestation } from '../../../shared/model/Prestation';
import { Location } from '../../../shared/model/location';
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
          dateStart: ['' , [Validators.required]],
          heureDebutPrestation: ['' , [Validators.required, this.formValidatorsService.timeValidator()]],
          city: ['', [Validators.required, Validators.maxLength(50)]],
          postalCode: ['', [Validators.required, this.formValidatorsService.postalCodeValidator(),Validators.maxLength(5)]],
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
        console.log(formData.dateStart);
        

        formData.dateStart = this.dateTimeService.formatYyyymmddToDdmmyyyy(formData.dateStart);

        if ( formData.dateStart.indexOf("/")<0 && typeof formData.dateStart !== 'string'&&
            formData.heureDebutPrestation.indexOf(":")<0 && typeof formData.heureDebutPrestation !== 'string'){
            return;
        }

        console.log(formData.dateStart);
        console.log(formData.heureDebutPrestation);

        this.dateDebut = this.dateTimeService.convertStrDateTimeToTimestamp(formData.dateStart, formData.heureDebutPrestation);
        this.dateFin = this.dateDebut + this.dateTimeService.convertTimeToMilliseconds(formData.duration);

        console.log(formData.dateStart);
        console.log(formData.heureDebutPrestation);

        const prestation = new Prestation();
        const location = new Location(formData.city, formData.postalCode);

        location.city

        prestation.id = formData.id;
        prestation.title = formData.title;
        prestation.duration = this.dateTimeService.convertTimeToMilliseconds(formData.duration);
        prestation.addPoint = 300;            //300 point par defaut
        prestation.dateStart = this.dateDebut;
        prestation.dateEnd = this.dateFin;
        prestation.state = "created";
        prestation.description = formData.description;
        prestation.maxUser = formData.maxUser;
        //prestation.location = location;
        

        if (this.selectedImage) {
            formDataAll.append('picture', this.selectedImage);
        }

        formDataAll.append('prestation', JSON.stringify(prestation));
        formDataAll.append('location', JSON.stringify(location));
        
        console.log(JSON.stringify(prestation));
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

 