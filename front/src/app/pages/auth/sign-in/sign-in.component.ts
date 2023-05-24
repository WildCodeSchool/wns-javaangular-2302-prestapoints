import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, ValidationErrors, Validators } from '@angular/forms';
import { SignInService } from '../service/signIn.service';
import { User } from 'src/app/shared/model/user';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent {

  constructor(private fb: FormBuilder, private signInService: SignInService) { }

  newUser?: User;
  createdUser: User = new User();

  signInForm = this.fb.group({
    firstname: ['', [Validators.required]],
    lastname: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(8), this.passwordValidator]],
    phone: ['', [Validators.required, this.phoneValidator]],
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
      this.signInService.createUser(this.newUser).subscribe((user) => {
        this.createdUser = user;
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

    const phoneRegex = RegExp('(\\d{10})');
    const valid = phoneRegex.test(control.value);

    const errors = {
      phone: {
        rules: 'Le numéro est incorrect'
      }
    };
    return !valid ? errors : null;
  }
}


