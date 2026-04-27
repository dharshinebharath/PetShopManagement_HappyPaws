import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeePetList } from './employee-pet-list';

describe('EmployeePetList', () => {
  let component: EmployeePetList;
  let fixture: ComponentFixture<EmployeePetList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmployeePetList],
    }).compileComponents();

    fixture = TestBed.createComponent(EmployeePetList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
