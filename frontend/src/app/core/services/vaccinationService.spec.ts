import { TestBed } from '@angular/core/testing';

import { VaccinationService } from './vaccinationService';

describe('Vaccination', () => {
  let service: VaccinationService ;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VaccinationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
