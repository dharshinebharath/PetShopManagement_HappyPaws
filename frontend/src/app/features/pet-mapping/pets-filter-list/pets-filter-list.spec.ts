// This test file checks the behavior around the pets filter list test flow.
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetsFilterList } from './pets-filter-list';

describe('PetsFilterList', () => {
  let component: PetsFilterList;
  let fixture: ComponentFixture<PetsFilterList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PetsFilterList],
    }).compileComponents();

    fixture = TestBed.createComponent(PetsFilterList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
