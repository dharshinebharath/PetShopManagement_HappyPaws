// This test file checks the behavior around the address test flow.
import { TestBed } from '@angular/core/testing';
import { AddressService } from './address';


describe('AddressService', () => {
  let service: AddressService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AddressService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
