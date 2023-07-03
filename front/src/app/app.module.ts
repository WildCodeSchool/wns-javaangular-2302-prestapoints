import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormBuilder, FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { FooterComponent } from './shared/components/footer/footer.component';
import { NavbarComponent } from './shared/components/navbar/navbar.component';
import { AlertComponent } from './shared/components/alert/alert.component';
import { ProfilComponent } from './shared/components/profil/profil.component';
import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/fr';
import { LOCALE_ID } from '@angular/core';
import { SharedModule } from './shared/shared.module';
import { FormUserModule } from './shared/components/form-user/form-user.module';
import { LocalStorageService } from './shared/services/localStorage.service';
import { ProfilService } from './shared/services/profil.service';
import { CategoryService } from './shared/services/category.service';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    NavbarComponent,
    AlertComponent,
    ProfilComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    FormUserModule
  ],
  providers: [FormBuilder, { provide: LOCALE_ID, useValue: 'fr' }, LocalStorageService, ProfilService, CategoryService],
  bootstrap: [AppComponent],
})
export class AppModule {
}

registerLocaleData(localeFr);
