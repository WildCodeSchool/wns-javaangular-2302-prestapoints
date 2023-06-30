import { Injectable } from '@angular/core';
import { AbstractControl, ValidatorFn } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class DateTimeValidatorsService {

  constructor() { }

  dateTimeCreatorToDate(date : string, time: string): Date {
    
    // Extraire les composantes de la date
    const dateComponents = date.split('/');
    const day = parseInt(dateComponents[0], 10);
    const month = parseInt(dateComponents[1], 10) - 1; // Les mois dans l'objet Date commencent à partir de zéro
    const year = parseInt(dateComponents[2], 10);
    
    // Extraire les composantes de l'heure
    const timeComponents = time.split(':');
    const hours = parseInt(timeComponents[0], 10);
    const minutes = parseInt(timeComponents[1], 10);
    
    // Créer l'objet Date
    const datetime = new Date(year, month, day, hours, minutes);
    return datetime;
  }

  dateTimeCreatorWithAddDuration(dateInput : Date, timeStr: string): Date {
    const dateTemp = dateInput;
    const [hours, minutes] = timeStr.split(':').map(Number);

    dateTemp.setHours(dateInput.getHours() + hours);
    dateTemp.setMinutes(dateInput.getMinutes() + minutes);

    return  dateTemp;
  }

  stringToDateTime(dateString: String):Date {
    // Découper la chaîne de caractères en composantes (jour, mois, année, heures, minutes)
    const [day, month, year, hours, minutes] = dateString.split(/[\/\s:]/);

    // Créer un nouvel objet Date en utilisant les composantes extraites
    const date = new Date(`${month}/${day}/${year} ${hours}:${minutes}`);
    
    return date;
  } 

  formatDateToString(date: Date): string {
    const day = date.getDate().toString().padStart(2, '0');
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const year = date.getFullYear().toString();
    const hours = date.getHours().toString().padStart(2, '0');
    const minutes = date.getMinutes().toString().padStart(2, '0');

    return `${day}/${month}/${year} ${hours}:${minutes}`;
  }

  formatYyyymmddToDdmmyyyy(dateString: String): string {
     // Découper la chaîne de caractères en composantes (jour, mois, année, heures, minutes)
     const [year, month, day] = dateString.split(/[\/\s:]/);
    
    return `${day}/${month}/${year}`;
  }

//----------------------------

    convertStrDateTimeToTimestamp(date : string, time: string):number{
        return  this.dateTimeCreatorToDate(date, time).getTime();
    }
  
    convertTimeToMilliseconds(time :String) : number {
        const [hours, minutes] = time.split(':');
        const date = new Date();
        const hoursToMinutesNumber : number  = parseInt(hours) * 60;
        const minutesToSecondeNumber : number  = (parseInt(minutes) + hoursToMinutesNumber) * 60;
        return minutesToSecondeNumber * 1000;
    }

    convertTimestampToDate(timestamp: number): String {
        const date = new Date(timestamp);
        const dateString = date.toLocaleString();
        return dateString;
      }
      
}
