import { Component, OnInit } from '@angular/core';
import { User } from '../../../shared/model/user';
import { DemoService } from '../demo-service/demo.service';

@Component({
  selector: 'app-demo',
  templateUrl: './demo.component.html',
  styleUrls: ['./demo.component.css'],
})
export class DemoComponent implements OnInit {
  public user: User = new User('');

  constructor(private service: DemoService) {}

  ngOnInit() {
    this.service.getTestName().subscribe((response) => {
      this.user = response;
    });
  }
}
