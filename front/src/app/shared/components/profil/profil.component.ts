import { Component } from '@angular/core';
import { LocalStorageService } from '../../services/localStorage.service';
import { ProfilService } from '../../services/profil.service';
import { User } from '../../model/user';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.scss']
})
export class ProfilComponent {
  
  newUser?: User;

  constructor(
    private profilService: ProfilService) { 
    }

    getUser(){
      this.profilService.getUserConnected().subscribe((response) => {
        this.newUser = response;
      });
    }
}
