import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeesPetList } from './employees-pet-list';

describe('EmployeesPetList', () => {
  let component: EmployeesPetList;
  let fixture: ComponentFixture<EmployeesPetList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmployeesPetList],
    }).compileComponents();

    fixture = TestBed.createComponent(EmployeesPetList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
