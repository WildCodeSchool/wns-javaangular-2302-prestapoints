import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserRegistrationsComponent } from './user-registrations.component';

describe('UserRegistrationsComponent', () => {
  let component: UserRegistrationsComponent;
  let fixture: ComponentFixture<UserRegistrationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserRegistrationsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserRegistrationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
