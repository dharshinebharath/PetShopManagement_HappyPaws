// This test file checks the behavior around the employee module test flow.
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeModule } from './employee-module';

describe('EmployeeModule', () => {
  let component: EmployeeModule;
  let fixture: ComponentFixture<EmployeeModule>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmployeeModule],
    }).compileComponents();

    fixture = TestBed.createComponent(EmployeeModule);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
