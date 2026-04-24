// This test file checks the behavior around the employee reports list test flow.
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeReportsList } from './employee-reports-list';

describe('EmployeeReportsList', () => {
  let component: EmployeeReportsList;
  let fixture: ComponentFixture<EmployeeReportsList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmployeeReportsList],
    }).compileComponents();

    fixture = TestBed.createComponent(EmployeeReportsList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
