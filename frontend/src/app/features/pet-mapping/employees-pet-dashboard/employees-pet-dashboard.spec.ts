// This test file checks the behavior around the employees pet dashboard test flow.
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeesPetDashboard } from './employees-pet-dashboard';

describe('EmployeesPetDashboard', () => {
  let component: EmployeesPetDashboard;
  let fixture: ComponentFixture<EmployeesPetDashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmployeesPetDashboard],
    }).compileComponents();

    fixture = TestBed.createComponent(EmployeesPetDashboard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
