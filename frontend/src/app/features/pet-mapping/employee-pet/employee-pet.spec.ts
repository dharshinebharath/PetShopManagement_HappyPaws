import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeePet } from './employee-pet';

describe('EmployeePet', () => {
  let component: EmployeePet;
  let fixture: ComponentFixture<EmployeePet>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmployeePet],
    }).compileComponents();

    fixture = TestBed.createComponent(EmployeePet);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
