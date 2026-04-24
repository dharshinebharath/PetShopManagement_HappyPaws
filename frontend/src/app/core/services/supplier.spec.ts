// This test file checks the behavior around the supplier test flow.
import { TestBed } from '@angular/core/testing';

import { SupplierService } from './supplier';

describe('SupplierService', () => {
  let service: SupplierService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SupplierService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
