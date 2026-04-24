// This test file checks the behavior around the auth service test flow.
import { TestBed } from '@angular/core/testing';

import { AuthService } from './authService';

describe('Auth', () => {
  let service: AuthService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
