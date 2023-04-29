import { TestBed } from '@angular/core/testing';

import { UserRegistrationsService } from './user-registrations.service';

describe('UserRegistrationsService', () => {
  let service: UserRegistrationsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserRegistrationsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
