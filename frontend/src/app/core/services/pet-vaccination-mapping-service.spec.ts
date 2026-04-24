// This test file checks the behavior around the pet vaccination mapping service test flow.
import { TestBed } from '@angular/core/testing';

import { PetVaccinationMappingService } from './pet-vaccination-mapping-service';

describe('PetVaccinationMappingService', () => {
  let service: PetVaccinationMappingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PetVaccinationMappingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
