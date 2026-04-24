// This test file checks the behavior around the pet vaccination list test flow.
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetVaccinationList } from './pet-vaccination-list';

describe('PetVaccinationList', () => {
  let component: PetVaccinationList;
  let fixture: ComponentFixture<PetVaccinationList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PetVaccinationList],
    }).compileComponents();

    fixture = TestBed.createComponent(PetVaccinationList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
