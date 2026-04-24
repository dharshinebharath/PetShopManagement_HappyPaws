// This test file checks the behavior around the employee reports dashboard test flow.
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeReportsDashboard } from './employee-reports-dashboard';

describe('EmployeeReportsDashboard', () => {
  let component: EmployeeReportsDashboard;
  let fixture: ComponentFixture<EmployeeReportsDashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmployeeReportsDashboard],
    }).compileComponents();

    fixture = TestBed.createComponent(EmployeeReportsDashboard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
