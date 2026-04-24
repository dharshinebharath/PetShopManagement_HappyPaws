// This test file checks the behavior around the address test flow.
import { TestBed } from '@angular/core/testing';

import { Address } from './address';

describe('Address', () => {
  let service: Address;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Address);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
