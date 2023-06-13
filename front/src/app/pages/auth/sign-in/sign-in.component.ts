import { Component, EventEmitter, Output } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  ValidationErrors,
  Validators,
} from '@angular/forms';
import { SignInService } from '../service/signIn.service';
import { User } from 'src/app/shared/model/user';
import { Alert } from 'src/app/shared/model/alert';
import { AlertEnum } from 'src/app/shared/enum/alert.enum';
import { AlertService } from 'src/app/shared/service/alert.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss'],
})
export class SignInComponent {
  newUser?: User;

  constructor(
    private fb: FormBuilder,
    private signInService: SignInService,
    private alertService: AlertService
  ) {}

  signInForm = this.fb.group({
    firstname: ['', [Validators.required]],
    lastname: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]],
    password: [
      '',
      [Validators.required, Validators.minLength(8), this.passwordValidator],
    ],
    confirmPassword: [
      '',
      [Validators.required, Validators.minLength(8), this.passwordValidator],
    ],
    phone: ['', [Validators.required, this.phoneValidator]],
  });

  async onSubmitFormSignIn() {
    const password = this.signInForm.get('password')?.value;
    const confirmPassword = this.signInForm.get('confirmPassword')?.value;
    const email = this.signInForm.get('email')?.value;

    if (email) {
      if (await this.verifyEmail(email)) {
        this.alertService.setAlert(
          AlertEnum.TYPE_DANGER,
          AlertEnum.MESSAGE_EMAIL_ALREADY_EXIST,
          true,
          AlertEnum.TIME_INFINITY
        );
      } else {
        if (password === confirmPassword) {
          if (this.signInForm.valid) {
            this.newUser = new User(
              this.signInForm.get('firstname')?.value,
              this.signInForm.get('lastname')?.value,
              this.signInForm.get('email')?.value,
              this.signInForm.get('password')?.value,
              this.signInForm.get('phone')?.value
            );
            this.signInService.createUser(this.newUser).subscribe(() => {
              this.alertService.setAlert(
                AlertEnum.TYPE_SUCCESS,
                AlertEnum.MESSAGE_SIGNIN_SUCCESS,
                true,
                AlertEnum.TIME_MEDIUM
              );
              this.signInForm.reset();
            });
          }
        } else {
          this.alertService.setAlert(
            AlertEnum.TYPE_DANGER,
            AlertEnum.MESSAGE_WRONG_PASSWORD,
            true,
            AlertEnum.TIME_INFINITY
          );
        }
      }
    }
  }

  passwordValidator(control: AbstractControl): ValidationErrors | null {
    const passwordRegex = RegExp('(?=.*[1-9])');
    const valid = passwordRegex.test(control.value);

    const errors = {
      password: {
        rules: 'Le mot de passe est non conforme',
      },
    };
    return !valid ? errors : null;
  }

  phoneValidator(control: AbstractControl): ValidationErrors | null {
    const phoneRegex = RegExp('(^0\\d{9})');
    const valid = phoneRegex.test(control.value);

    const errors = {
      phone: {
        rules: 'Le numéro est non conforme',
      },
    };
    return !valid ? errors : null;
  }

  private verifyEmail(email: string): Promise<boolean> {
    return new Promise<boolean>((isPresent) => {
      this.signInService.verifyEmail(email).subscribe((emailExists) => {
        isPresent(emailExists);
      });
    });
  }
}
