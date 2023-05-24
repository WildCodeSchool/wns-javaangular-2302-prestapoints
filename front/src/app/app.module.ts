import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormBuilder, FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { DemoComponent } from './pages/demo/demo-component/demo.component';
import { FooterComponent } from './shared/components/footer/footer.component';

@NgModule({
  declarations: [AppComponent, DemoComponent],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule,FormsModule,ReactiveFormsModule,],
  providers: [FormBuilder],
  declarations: [AppComponent, DemoComponent, FooterComponent],
  imports: [
    BrowserModule,
    AppRoutingModule, 
    HttpClientModule
    
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
