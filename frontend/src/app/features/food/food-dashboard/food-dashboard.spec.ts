// This test file checks the behavior around the food dashboard test flow.
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FoodDashboard } from './food-dashboard';

describe('FoodDashboard', () => {
  let component: FoodDashboard;
  let fixture: ComponentFixture<FoodDashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FoodDashboard]
    }).compileComponents();

    fixture = TestBed.createComponent(FoodDashboard);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
