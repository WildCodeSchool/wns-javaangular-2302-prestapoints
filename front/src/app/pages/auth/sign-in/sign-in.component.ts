import { Component, EventEmitter, Output } from '@angular/core';
import { AbstractControl, FormBuilder, ValidationErrors, Validators } from '@angular/forms';
import { SignInService } from '../service/signIn.service';
import { User } from 'src/app/shared/model/user';
import { Alert } from 'src/app/shared/model/alert';
import { AlertEnum } from 'src/app/shared/enum/alert-enum';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent {

  constructor(private fb: FormBuilder, private signInService: SignInService) { }

  newUser?: User;
  alert: Alert = new Alert();

  @Output()
  alertToSend: EventEmitter<Alert> = new EventEmitter();

  signInForm = this.fb.group({
    firstname: ['', [Validators.required]],
    lastname: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(8), this.passwordValidator]],
    phone: ['', [Validators.required]],
  });

  onSubmitFormSignIn() {
    if (this.signInForm.valid) {
      this.newUser = new User(
        this.signInForm.get('firstname')?.value,
        this.signInForm.get('lastname')?.value,
        this.signInForm.get('email')?.value,
        this.signInForm.get('password')?.value,
        this.signInForm.get('phone')?.value,
      )

      this.alert.type = "info";
      this.alert.message = "Merci pour votre inscription, veuillez vous connecter afin d'accéder au site.";
      this.alert.timer = true;

      this.signInService.createUser(this.newUser).subscribe(() => {
        this.startAlertTimer(AlertEnum.TYPE_SUCCESS, AlertEnum.MESSAGE_SIGNIN_SUCCESS, true, AlertEnum.TIME_LONG);
        this.signInForm.reset();
      });
    }
  }

  passwordValidator(control: AbstractControl): ValidationErrors | null {
    const passwordRegex = RegExp('(?=.*[1-9])');
    const valid = passwordRegex.test(control.value);

    const errors = {
      password: {
        rules: 'Doit contenit au moins un chiffre'
      }
    };
    return !valid ? errors : null;
  }

  phoneValidator(control: AbstractControl): ValidationErrors | null {

    // TODO revoir la regex du numéro de téléphone : 

    const phoneRegex = RegExp('(^0[1-9]\d{8}$)');
    const valid = phoneRegex.test(control.value);

    const errors = {
      phone: {
        rules: 'Le numéro est incorrect'
      }
    };
    return !valid ? errors : null;
  }

  private startAlertTimer(type: string, message: string, timer: boolean, duration: number): void {
    this.sendAlert(type, message, timer);
    setTimeout(() => {
      this.alert.timer = false; // Réinitialiser l'alerte pour la faire disparaître
      this.sendAlert(type, message, false);
    }, duration);
  }
  
  private sendAlert(type: string, message: string, timer: boolean): void {
    this.alert.type = type;
    this.alert.message = message;
    this.alert.timer = timer;
    this.alertToSend.emit(this.alert);
  }
}

