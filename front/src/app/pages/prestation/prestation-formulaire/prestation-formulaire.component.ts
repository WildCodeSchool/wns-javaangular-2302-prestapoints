
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Component,OnInit } from '@angular/core';
import { Prestation } from '../../../shared/model/prestation';
import { Location } from '../../../shared/model/location';
import { HttpClient } from '@angular/common/http';
import { FormValidatorsService } from 'src/app/shared/services/formValidators.service';
import { CategoryService } from 'src/app/shared/services/category.service';
import { Category } from 'src/app/shared/model/category';


@Component({
  selector: 'app-prestation-formulaire',
  templateUrl: './prestation-formulaire.component.html',
  styleUrls: ['./prestation-formulaire.component.scss']
})
export class PrestationFormulaireComponent {
    prestation!: Prestation;
    location!: Location;
    categories!: Category[];

    prestationFormBasic!: FormGroup;
    prestationFormDescription!: FormGroup;
    prestationFormLocation!: FormGroup;
    prestationFormMedia!: FormGroup;
    prestationFormCharacteristic!:  FormGroup;



    formFieldsBasic: { name: string; label: string; type: string; validationMessage: string }[] = [
        { name: 'title', label: 'Titre', type: 'text', validationMessage: 'Le champs est limité à 50 caractères.' },
        { name: 'type', label: 'Type', type: 'text', validationMessage: 'Le champs est limité à 50 caractères.' },
        { name: 'category', label: 'Catégorie', type: 'text', validationMessage: 'Le champs est limité à 50 caractères.' },
    ];

    formFieldsCharacteristic: { name: string; label: string; type: string; validationMessage: string }[] = [
        { name: 'dateStart', label: 'Date de début', type: 'date', validationMessage: 'La date doit être sous forme jj/mm/aaaa et supérieur à aujourdui.' },
        { name: 'heureDebutPrestation', label: 'Heure de début', type: 'text', validationMessage: "On acceepte que des nombres" },
        { name: 'duration', label: 'Durée de la prestation en Heure', type: 'number', validationMessage: "L'horraire n'est pas conforme hh:mm." },
        { name: 'maxUser', label: 'Nombre maximal d\'utilisateurs', type: 'number', validationMessage: 'L\'information du nombre de participants maximal est obligatoire.' }
    ];

    formFieldsDescription: { name: string; label: string; type: string; validationMessage: string }[] = [
        { name: 'practicalInformation', label: 'Informations pratique', type: 'text', validationMessage: 'Le champs est limité à 255 caractères.' },
        { name: 'description', label: 'Description', type: 'textarea', validationMessage: 'Une description de la prestation est obligatoire.' },
        { name: 'littleDescription', label: 'Petite description', type: 'text', validationMessage: 'Le champs est limité à 50 caractères.' },
        { name: 'language', label: 'Durée de la prestation', type: 'text', validationMessage: "Le champs est limité à 50 caractères." },
        { name: 'personalInfos', label: 'Quelques informations personnelles', type: 'text', validationMessage: 'Le champs est limité à 255 caractères.' },
    ];

    formFieldsLocation: { name: string; label: string; type: string; validationMessage: string }[] = [
        { name: 'LocationAddressNumber', label: 'Numéro de la rue', type: 'text', validationMessage: "L'horraire n'est pas conforme hh:mm." },
        { name: 'LocationAddress', label: 'Nom de la rue', type: 'text', validationMessage: 'Le champs est limité à 50 caractères.' },
        { name: 'postalCode', label: 'Code postal', type: 'text', validationMessage: 'Le champs doit être de ce format 45000.' },
        { name: 'city', label: 'Nom de la ville', type: 'text', validationMessage: 'Le champs est limité à 50 caractères.' },
        { name: 'LocationAddressInformation', label: 'Informations complémentaires sur l\'adresse', type: 'text', validationMessage: 'Le champs est limité à 255 caractères.' }, 
    ];

    constructor(private formBuilder: FormBuilder, private http: HttpClient, private formValidatorsService: FormValidatorsService, private categoryService: CategoryService) {
  
            this.categoriesSubscription();

        

     }
  
    categoriesSubscription() {
        this.categoryService.getCategories().subscribe((categories) => {
            this.categories = categories;
        });
    }

    ngOnInit(): void {
        console.log("categories : " + this.categories);

        this.prestationFormBasic = this.formBuilder.group({
            title: ['', [Validators.required, Validators.maxLength(50)]],
            type: ['', [Validators.required, Validators.maxLength(50)]],
            category: ['', [Validators.required, Validators.maxLength(50)]],
            duration: ['', [Validators.required, ]],
            dateStart: ['', [Validators.required, this.formValidatorsService.dateValidator()]],
            maxUser: ['', [Validators.required, Validators.maxLength(3)]]
        });

        this.prestationFormCharacteristic = this.formBuilder.group({
            duration: ['', [Validators.required, ]],
            dateStart: ['', [Validators.required, this.formValidatorsService.dateValidator()]],
            maxUser: ['', [Validators.required, Validators.maxLength(3)]]
        });

        this.prestationFormDescription = this.formBuilder.group({
            practicalInformation: ['', Validators.required, Validators.maxLength(255)],
            description: ['', Validators.required, Validators.maxLength(1000)],
            littleDescription: ['', Validators.required, Validators.maxLength(255)],
            language: ['', Validators.required, Validators.maxLength(50)],
            personalInfos: ['', Validators.required, Validators.maxLength(255)],
        });

        this.prestationFormLocation = this.formBuilder.group({
            LocationCity: ['', Validators.required, Validators.maxLength(50)],
            LocationPostalCode: ['', Validators.required, this.formValidatorsService.postalCodeValidator(),Validators.maxLength(5)],
            LocationAddress: ['', Validators.required, Validators.maxLength(50)],
            LocationAddressNumber: ['', Validators.required, Validators.maxLength(50)],
            LocationAddressInformation: ['', Validators.required, Validators.maxLength(255)],
        });

        this.prestationFormMedia= this.formBuilder.group({
            mediaImage: ['', Validators.required],
            mediaVideo: ['', Validators.required, Validators.maxLength(255)],
        });
      }
  
    onSubmit() {
        
        if (this.prestationFormBasic.invalid) {
          return;
        }

        const formDataBasic = this.prestationFormBasic.value;
        const formDataDescription = this.prestationFormDescription.value;
        const formDataLocation = this.prestationFormLocation.value;
        const formDataCharacteristic = this.prestationFormCharacteristic.value;

        this.prestation.title = formDataBasic.title;
        this.prestation.type = formDataBasic.type;
        this.prestation.category = formDataBasic.category;

        const date = new Date(formDataCharacteristic.dateStart);
        this.prestation.dateStartTimestamps = date.getTime();
        this.prestation.duration = formDataCharacteristic.duration;
        this.prestation.maxUser = formDataCharacteristic.maxUser;

        
       this.prestation.description = formDataDescription.description;
       this.prestation.littleDescription = formDataDescription.littleDescription;
       
       this.prestation.practicalInformation = formDataDescription.practicalInformation;
       this.prestation.language = formDataDescription.language;
       this.prestation.personalInfos = formDataDescription.personalInfos;
       this.prestation.registrations = formDataDescription.registrations;

       
       this.location.city = formDataLocation.LocationCity;
       this.location.postalCode = formDataLocation.LocationPostalCode;
       this.location.address = formDataLocation.LocationAddress;
       this.location.addressNumber = formDataLocation.LocationAddressNumber;
       this.location.addressInformation = formDataLocation.LocationAddressInformation;
       this.prestation.location = this.location;

       this.prestation.videoLink = formDataLocation.videoLink;
       this.prestation.images = formDataLocation.images;
       
    }

    savePrestationToBack(prestation : Prestation){
        // Envoie de l'objet JSON au serveur
        this.http.post("http://localhost:8080/prestations", this.prestation).subscribe(
            response => {
            // Traitement de la réponse du serveur
            this.prestationFormBasic.reset();
            },
            error => {
            // Gestion des erreurs
            console.error(error);
            }
        );  
    }

  }