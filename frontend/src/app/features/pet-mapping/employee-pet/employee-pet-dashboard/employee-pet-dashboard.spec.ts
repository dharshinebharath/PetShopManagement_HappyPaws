import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeePetDashboard } from './employee-pet-dashboard';

describe('EmployeePetDashboard', () => {
  let component: EmployeePetDashboard;
  let fixture: ComponentFixture<EmployeePetDashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmployeePetDashboard],
    }).compileComponents();

    fixture = TestBed.createComponent(EmployeePetDashboard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
