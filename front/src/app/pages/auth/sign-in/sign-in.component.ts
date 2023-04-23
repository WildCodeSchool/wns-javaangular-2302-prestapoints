import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, ValidationErrors, Validators } from '@angular/forms';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent {

  constructor(private fb: FormBuilder) { }

  signInForm = this.fb.group({
    firstname: ['', [Validators.required]],
    lastname: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(8), this.passwordValidator]],
    phone: ['', [Validators.required]],
  });
  
  onSubmitFormSignIn(){
    console.log(this.signInForm.value);   
  }

  passwordValidator(control: AbstractControl): ValidationErrors | null{
    const passwordRegex = RegExp('(?=.*[1-9])');
    const valid = passwordRegex.test(control.value);

    const errors = {
        password: {
            rules: 'Doit contenit au moins un chiffre'
        }
    };
    return !valid ? errors : null;
  }
}


