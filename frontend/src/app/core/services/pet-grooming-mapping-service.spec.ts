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
