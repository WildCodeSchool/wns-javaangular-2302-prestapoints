import { Component, OnInit } from '@angular/core';
import { LocalStorageService } from '../../services/localStorage.service';
import { ProfilService } from '../../services/profil.service';
import { User } from '../../model/user';
import { ResponseApi } from '../../model/responseApi';
import { AbstractControl, FormBuilder, ValidationErrors, Validators } from '@angular/forms';
import { SignInService } from '../form-user/service/signIn.service';
import { AlertService } from '../../services/alert.service';
//import * as bcrypt from 'bcryptjs';
import { HttpErrorResponse } from '@angular/common/http';


@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.scss']
})
export class ProfilComponent implements OnInit{

  userUpdate?: User;
  userResponse?: User;
  responseApi?: ResponseApi;
  isLoggedIn = false;
  visible = true;
  email: string = "";
  avatarData: any;
  image: any;

  constructor(
    private fb: FormBuilder,
    private localstorageService: LocalStorageService,
    private profilService: ProfilService
  ) {
  }

  //lastPasswordValidator = (): ValidationErrors | null => {
  //  const passwordForm = this.profilForm.get('lastPassword');
  //  const passwordValue = passwordForm?.value;
//
  //  if (passwordForm && typeof passwordValue === 'string') {
  //    const passwordsMatch = this.comparePassword();
  //    if (!passwordsMatch) {
  //      const errors = {
  //        lastPassword: {
  //          rules: 'Le mot de passe ne correspond pas à l\'ancien'
  //        }
  //      };
  //      return errors;
  //    }
  //  }
  //  return null;
  //}

  confirmPasswordValidator = (): ValidationErrors | null => {
    const password = this.profilForm.get('password')?.value;
    const passwordConfirm = this.profilForm.get('confirmPassword')?.value;

    if (password !== passwordConfirm) {
      const errors = {
        confirmPassword: {
          rules: 'Les mots de passe ne sont pas identiques'
        }
      };
      return errors;
    }
    return null;
  }


  passwordValidator = (control: AbstractControl): ValidationErrors | null => {
    const passwordRegex = RegExp('(?=.*[1-9])');
    const lastPassword = this.profilForm.get('lastPassword')?.value;
    const passwordConfirm = this.profilForm.get('password')?.value;

    if (!passwordRegex.test(control.value)) {
      const errors = {
        password: {
          rules: 'Le mot de passe est non conforme',
        },
      };
      return errors;

    }

    if (lastPassword === control.value) {
      const errors = {
        password: {
          rules: 'Le mot de passe est identique à votre ancien mot de passe',
        },
      };
      return errors;
    }
    return null;
  }



  profilForm = this.fb.group({
    firstname: [{ value: '', disabled: true }, [Validators.required]],
    lastname: [{ value: '', disabled: true }, [Validators.required]],
    email: [{ value: '', disabled: true }, [Validators.required, Validators.email]],
    password: [{ value: '', disabled: true }, [Validators.required, Validators.minLength(8), this.passwordValidator.bind(this)]],
    confirmPassword: [{ value: '', disabled: true }, [Validators.required, Validators.minLength(8), this.confirmPasswordValidator.bind(this)]],
    phone: [{ value: '', disabled: true }, [Validators.required, this.phoneValidator]],
  });


  ngOnInit() {
    this.checkUserLoggedIn();
  }

  checkUserLoggedIn() {
    const value = this.localstorageService.getItem('currentUser');
    if (value != null) {
      this.isLoggedIn = true
      this.visible = false;
      this.getUser();
    }
  }

  getUser() {
    this.profilService.getUserConnected().subscribe((response) => {
      this.userResponse = response;
      if (this.userResponse) {
        this.profilForm.patchValue({ firstname: this.userResponse.firstname });
        this.profilForm.patchValue({ lastname: this.userResponse.lastname });
        this.profilForm.patchValue({ email: this.userResponse.email });
        this.profilForm.patchValue({ phone: this.userResponse.phone });
        this.getImageAvatar(); 
      }
    });
  }

  resetForm() {
    this.profilForm.get('lastPassword')?.reset();
    this.profilForm.get('password')?.reset();
    this.profilForm.get('confirmPassword')?.reset();
    this.getImageAvatar();
  }

  getImageAvatar() {
    this.profilService.getAvatar().subscribe((data) => {
      this.avatarData = data;
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.image = e.target.result;
      };
      reader.readAsDataURL(new Blob([this.avatarData]));
    });
  }

  refreshAvatarImage() {
    this.image = null;
    this.getImageAvatar();
  }

  toggleFormEditability() {
    const formControls = this.profilForm.controls;
    Object.keys(formControls).forEach(key => {
      const control = formControls[key as keyof typeof formControls];
      if (control.disabled) {
        this.visible = true
        control.enable();
      } else {
        this.visible = false
        this.profilForm.get('lastPassword')?.reset();
        this.profilForm.get('password')?.reset();
        this.profilForm.get('confirmPassword')?.reset();
        this.getImageAvatar();
        control.disable();
      }
    });
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.image = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  }

  async onSubmitFormLogin() {
    const password = this.profilForm.get('password')?.value;
    const confirmPassword = this.profilForm.get('confirmPassword')?.value;


    if (password === confirmPassword) {
      this.userUpdate = new User(
        this.profilForm.get('firstname')?.value,
        this.profilForm.get('lastname')?.value,
        this.profilForm.get('email')?.value,
        this.profilForm.get('password')?.value,
        this.profilForm.get('phone')?.value
      );

      const imageFormData = new FormData();

      const imageFile = (document.getElementById('formFile') as HTMLInputElement)?.files?.[0];
      if (imageFile) {
        imageFormData.append('image', imageFile, imageFile.name);
      }
      this.profilService.updateAvatar(imageFormData).subscribe(
        (response) => {
          this.responseApi = response;
          this.refreshAvatarImage();
        },
        (error: HttpErrorResponse) => {
        }
      );

      this.profilService.updateUser(this.userUpdate).subscribe(
        (response) => {
          this.responseApi = response;
        },
        (error: HttpErrorResponse) => {
        }
      );
      this.profilForm.get('password')?.reset();
      this.profilForm.get('lastPassword')?.reset();
      this.profilForm.get('confirmPassword')?.reset();
      this.toggleFormEditability();

    }
  }

  refreshPage() {
    window.location.reload();
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
}
