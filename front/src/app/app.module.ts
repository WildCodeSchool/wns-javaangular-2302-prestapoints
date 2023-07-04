import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormBuilder, FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { FooterComponent } from './shared/components/footer/footer.component';
import { NavbarComponent } from './shared/components/navbar/navbar.component';
import { AlertComponent } from './shared/components/alert/alert.component';
import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/fr';
import { LOCALE_ID } from '@angular/core';
import { CategoryService } from './shared/services/category.service';
import { AuthInterceptor } from './core/service/auth/auth.interceptor';
import { AuthenticationService } from './core/service/auth/authentication.service';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    NavbarComponent,
    AlertComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [
    FormBuilder,
    { provide: LOCALE_ID, useValue: 'fr' },
    CategoryService,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    AuthenticationService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}

registerLocaleData(localeFr);
