// This test file checks the behavior around the pet grooming mapping service test flow.
import { TestBed } from '@angular/core/testing';

import { PetGroomingMappingService } from './pet-grooming-mapping-service';

describe('PetGroomingMappingService', () => {
  let service: PetGroomingMappingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PetGroomingMappingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
