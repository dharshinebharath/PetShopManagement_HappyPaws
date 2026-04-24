// This test file checks the behavior around the pet grooming list test flow.
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetGroomingList } from './pet-grooming-list';

describe('PetGroomingList', () => {
  let component: PetGroomingList;
  let fixture: ComponentFixture<PetGroomingList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PetGroomingList],
    }).compileComponents();

    fixture = TestBed.createComponent(PetGroomingList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
