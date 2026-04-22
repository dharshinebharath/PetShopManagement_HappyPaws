import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FoodDashboard } from './food-dashboard';

describe('FoodDashboard', () => {
  let component: FoodDashboard;
  let fixture: ComponentFixture<FoodDashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FoodDashboard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FoodDashboard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
