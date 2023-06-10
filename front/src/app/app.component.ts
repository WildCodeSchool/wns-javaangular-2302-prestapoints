import { Component, Input } from '@angular/core';
import '/node_modules/bootstrap/dist/js/bootstrap.min.js';
import { Alert } from './shared/model/alert';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'front';
}
