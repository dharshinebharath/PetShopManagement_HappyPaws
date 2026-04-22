import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetVaccinationDashboard } from './pet-vaccination-dashboard';

describe('PetVaccinationDashboard', () => {
  let component: PetVaccinationDashboard;
  let fixture: ComponentFixture<PetVaccinationDashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PetVaccinationDashboard],
    }).compileComponents();

    fixture = TestBed.createComponent(PetVaccinationDashboard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
