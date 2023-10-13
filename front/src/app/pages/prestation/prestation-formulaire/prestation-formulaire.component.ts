
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
    isLoaded: boolean = false;
    prestationFormBasic!: FormGroup;
    prestationFormDescription!: FormGroup;
    prestationFormLocation!: FormGroup;
    prestationFormMedia!: FormGroup;
    prestationFormCharacteristic!:  FormGroup;
    pageNumber: number = 0;


    formFieldsBasic: { name: string; label: string; type: string; placeholder: string; validationMessage: string }[] = [
        { name: 'type', label: 'Type', type: 'text', placeholder: "ex : Dressage de chiens", validationMessage: 'Le champs est limité à 50 caractères.' },
        { name: 'title', label: 'Titre', type: 'text', placeholder: "ex : Apprenez à dresser votre chien avec notre expert canin", validationMessage: 'Le champs est limité à 50 caractères.' },
    ];

    formFieldsCharacteristic: { name: string; label: string; type: string; placeholder: string; validationMessage: string }[] = [
        { name: 'dateStart', label: 'Date de début', type: 'date', placeholder: "", validationMessage: 'La date doit être sous forme jj/mm/aaaa et supérieur à aujourdui.' },
        { name: 'timeStart', label: 'Heure de début', type: 'text', placeholder: "ex : 16:00", validationMessage: "On acceepte que des nombres" },
        { name: 'duration', label: 'Durée de la prestation en Heure', type: 'number', placeholder: "ex : 3", validationMessage: "L'horraire n'est pas conforme hh:mm." },
        { name: 'maxUser', label: 'Nombre maximal d\'utilisateurs', type: 'number', placeholder: "ex : 6", validationMessage: 'L\'information du nombre de participants maximal est obligatoire.' }
    ];

    formFieldsDescription: { name: string; label: string; type: string; placeholder: string; validationMessage: string }[] = [
        { name: 'practicalInformation', label: 'Informations pratique', type: 'text', placeholder: "ex : Les chiens admis sont des chiens dressés ...", validationMessage: 'ex : Le champs est limité à 255 caractères.' },
        { name: 'description', label: 'Description', type: 'textarea', placeholder: "ex : Découvrez les techniques de dressage de chiens et renforcez ...", validationMessage: 'Une description de la prestation est obligatoire.' },
        { name: 'littleDescription', label: 'Petite description', type: 'text', placeholder: "ex : Découvrez les techniques de dressage de chiens ...", validationMessage: 'Le champs est limité à 50 caractères.' },
        { name: 'language', label: 'Durée de la prestation', type: 'text', placeholder: "ex : francais", validationMessage: "Le champs est limité à 50 caractères." },
        { name: 'personalInfos', label: 'Quelques informations personnelles', type: 'text', placeholder: "ex : Notre expert canin possède une vaste expérience ...", validationMessage: 'Le champs est limité à 255 caractères.' },
    ];

    formFieldsLocation: { name: string; label: string; type: string; placeholder: string; validationMessage: string }[] = [
        { name: 'LocationAddressNumber', label: 'Numéro de la rue', type: 'text', placeholder: "ex : 25bis", validationMessage: "L'horraire n'est pas conforme hh:mm." },
        { name: 'LocationAddress', label: 'Nom de la rue', type: 'text', placeholder: "ex : rue de la Forge", validationMessage: 'Le champs est limité à 50 caractères.' },
        { name: 'LocationPostalCode', label: 'Code postal', type: 'text', placeholder: "ex : 45000", validationMessage: 'Le champs doit être de ce format 45000.' },
        { name: 'LocationCity', label: 'Nom de la ville', type: 'text', placeholder: "ex : ORLEANS", validationMessage: 'Le champs est limité à 50 caractères.' },
        { name: 'LocationAddressInformation', label: 'Informations complémentaires sur l\'adresse', type: 'text', placeholder: "ex : L'atelier de Modesto se trouve dans la rue Gerlach Ports ...", validationMessage: 'Le champs est limité à 255 caractères.' }, 
    ];

    constructor(private formBuilder: FormBuilder, private http: HttpClient, private formValidatorsService: FormValidatorsService, private categoryService: CategoryService) {
  
        this.categoryService.getCategories().subscribe((categories) => {
            this.categories = categories;
            this.isLoaded = true;
            console.log("categories : " + this.categories);
        });
     }
  

    ngOnInit(): void {
    
        this.prestationFormBasic = this.formBuilder.group({
            title: ['', [Validators.required, Validators.maxLength(50)]],
            type: ['', [Validators.required, Validators.maxLength(50)]],
            category: [],
        });

        this.prestationFormCharacteristic = this.formBuilder.group({
        dateStart: ['', [Validators.required, this.formValidatorsService.dateValidator()]],
        timeStart: ['', [Validators.required, this.formValidatorsService.timeValidator]],
            duration: ['', [Validators.required, Validators.maxLength(2)]],
            maxUser: ['', [Validators.required, Validators.maxLength(3)]],
        });

        this.prestationFormDescription = this.formBuilder.group({
            practicalInformation: ['',[ Validators.required, Validators.maxLength(255)]],
            description: ['', [Validators.required, Validators.maxLength(1000)]],
            littleDescription: ['',[ Validators.required, Validators.maxLength(255)]],
            language: ['', [Validators.required, Validators.maxLength(50)]],
            personalInfos: ['', [Validators.required, Validators.maxLength(255)]],
        });

        this.prestationFormLocation = this.formBuilder.group({
            LocationCity: ['', [Validators.required, Validators.maxLength(50)]],
            LocationPostalCode: ['', [Validators.required, this.formValidatorsService.postalCodeValidator(),Validators.maxLength(5)]],
            LocationAddress: ['', [Validators.required, Validators.maxLength(50)]],
            LocationAddressNumber: ['', [Validators.required, Validators.maxLength(50)]],
            LocationAddressInformation: ['', [Validators.required, Validators.maxLength(255)]],
        });

        this.prestationFormMedia= this.formBuilder.group({
            mediaImage: ['', Validators.required],
            mediaVideo: ['', Validators.required, Validators.maxLength(255)],
        });
    }

    onSubmitBpNext(pageNumber: number){
        /*if (pageNumber == 0 && this.prestationFormBasic.invalid) {
            return;
        }
        if (pageNumber == 1 && this.prestationFormCharacteristic.invalid) {
            return;
        }
        if (pageNumber == 2 && this.prestationFormDescription.invalid) {
            return;
        }*/

        
        this.pageNumber = pageNumber + 1;
    }

    onSubmitBpPrevious(pageNumber: number){
        console.log("je suis dedans");
        this.pageNumber = pageNumber - 1;
    }

    onSubmit() {
        
        if (this.prestationFormLocation.invalid) {
          return;
        }

        const formDataBasic = this.prestationFormBasic.value;
        const formDataDescription = this.prestationFormDescription.value;
        const formDataLocation = this.prestationFormLocation.value;
        const formDataCharacteristic = this.prestationFormCharacteristic.value;

        this.prestation.title = formDataBasic.title;
        this.prestation.type = formDataBasic.type;
        this.prestation.category = formDataBasic.category;


        const [jour, mois, annee] = formDataCharacteristic.dateStart.split('/');
        const [heures, minutes] = formDataCharacteristic.timeStart.split(':');
        const date = new Date(annee, mois - 1, jour, heures, minutes); 
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