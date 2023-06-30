import { Component } from '@angular/core';
import { FormBuilder, Validators, FormControl } from '@angular/forms';
import { ProfilService } from '../../services/profil.service';
import { User } from '../../model/user';
import { SignInService } from 'src/app/pages/auth/sign-in/service/signIn.service';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.scss']
})
export class ProfilComponent {

  isDisable: boolean  = true;
  isEditing: boolean = false;
  userUpdate?: User;

  constructor(
    private fb: FormBuilder,
    private profilService: ProfilService,
    private signIn: SignInService
  ) { }

  profilForm = this.fb.group({
    firstname: [{ value: '', disabled: true }],
    lastname: [{ value: '', disabled: true }],
    email: [{ value: '', disabled: true }],
    password: [
      { value: '', disabled: true },
      [Validators.required, Validators.minLength(8)],
    ],
     phone: [{ value: '', disabled: true }, [Validators.required]],
  });

  onSubmitFormProfil(){
    console.log("je suis la");
    
      this.userUpdate = new User(
        this.profilForm.get('firstname')?.value,
        this.profilForm.get('lastname')?.value,
        this.profilForm.get('email')?.value,
        this.profilForm.get('password')?.value,
        this.profilForm.get('phone')?.value
      );

    console.log(this.userUpdate);
    this.profilService.updateUser(this.userUpdate)
  }

  onEdit(){
    if(this.isDisable){
      this.profilForm.enable();
      this.isEditing = true;
    }else{
      this.profilForm.disable();
      this.isEditing = false;
    }
    this.isDisable = !this.isDisable
  }
}
