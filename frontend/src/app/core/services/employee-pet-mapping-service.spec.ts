// This test file checks the behavior around the employee pet mapping service test flow.
import { TestBed } from '@angular/core/testing';

import { EmployeePetMappingService } from './employee-pet-mapping-service';

describe('EmployeePetMappingService', () => {
  let service: EmployeePetMappingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmployeePetMappingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
