import { TestBed } from '@angular/core/testing';

import { Vaccination } from './vaccination';

describe('Vaccination', () => {
  let service: Vaccination;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Vaccination);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
