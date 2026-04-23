import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetFoodDashboard } from './pet-food-dashboard';

describe('PetFoodDashboard', () => {
  let component: PetFoodDashboard;
  let fixture: ComponentFixture<PetFoodDashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PetFoodDashboard],
    }).compileComponents();

    fixture = TestBed.createComponent(PetFoodDashboard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
