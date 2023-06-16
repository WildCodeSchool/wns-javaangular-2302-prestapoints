import { Injectable } from '@angular/core';
import { AbstractControl, ValidatorFn } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class DateTimeValidatorsService {

  constructor() { }

  dateValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const dateRegex = /^\d{2}\/\d{2}\/\d{4}$/;
      const isValid = dateRegex.test(control.value);
      return !isValid ? { invalidDate: { value: control.value } } : null;
    };
  }

  timeValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const timeRegex = /^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/;
      const isValid = timeRegex.test(control.value);
      return !isValid ? { invalidTime: { value: control.value } } : null;
    };
  }

  dateTimeCreator(date : string, time: string): Date {
    
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

    const [hours, minutes] = timeStr.split(':').map(Number);

    dateInput.setHours(dateInput.getHours() + hours);
    dateInput.setMinutes(dateInput.getMinutes() + minutes);

    return dateInput;
  }
}
