import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetFoodList } from './pet-food-list';

describe('PetFoodList', () => {
  let component: PetFoodList;
  let fixture: ComponentFixture<PetFoodList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PetFoodList],
    }).compileComponents();

    fixture = TestBed.createComponent(PetFoodList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
