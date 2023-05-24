import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { DemoComponent } from './pages/demo/demo-component/demo.component';
import { FooterComponent } from './shared/components/footer/footer.component';

@NgModule({
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
