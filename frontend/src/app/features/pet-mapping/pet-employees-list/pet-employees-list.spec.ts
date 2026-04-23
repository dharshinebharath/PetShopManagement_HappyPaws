import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetEmployeesList } from './pet-employees-list';

describe('PetEmployeesList', () => {
  let component: PetEmployeesList;
  let fixture: ComponentFixture<PetEmployeesList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PetEmployeesList],
    }).compileComponents();

    fixture = TestBed.createComponent(PetEmployeesList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
