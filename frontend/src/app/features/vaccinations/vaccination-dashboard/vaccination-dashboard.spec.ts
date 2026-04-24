// This test file checks the behavior around the vaccination dashboard test flow.
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VaccinationDashboard } from './vaccination-dashboard';

describe('VaccinationDashboard', () => {
  let component: VaccinationDashboard;
  let fixture: ComponentFixture<VaccinationDashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VaccinationDashboard],
    }).compileComponents();

    fixture = TestBed.createComponent(VaccinationDashboard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
