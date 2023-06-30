import { Injectable } from '@angular/core';
import { AbstractControl, ValidatorFn } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class FormValidatorsService {

  constructor() { }


    DateToDayToString():String{
        const aujourdHui = new Date();
        const jour = aujourdHui.getDate();
        const mois = aujourdHui.getMonth() + 1; // Les mois commencent à 0
        const annee = aujourdHui.getFullYear();
        return `${jour.toString().padStart(2, '0')}/${mois.toString().padStart(2, '0')}/${annee}`;
    }

    // Validation personnalisée pour vérifier la valeur maximale
    validateMaxValue(maxValue: number): ValidatorFn {
        return (control) => {
          const value = control.value;
          if (value > maxValue) {
            return { maxExceeded: true };
          }
          return null;
        };
    }

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

}
