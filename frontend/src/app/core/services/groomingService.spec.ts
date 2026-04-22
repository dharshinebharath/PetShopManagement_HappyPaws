import { TestBed } from '@angular/core/testing';

import { Grooming } from './groomingService';

describe('Grooming', () => {
  let service: Grooming;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Grooming);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
