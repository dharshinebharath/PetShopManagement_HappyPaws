import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoryDashboard } from './category-dashboard';

describe('CategoryDashboard', () => {
  let component: CategoryDashboard;
  let fixture: ComponentFixture<CategoryDashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CategoryDashboard],
    }).compileComponents();

    fixture = TestBed.createComponent(CategoryDashboard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
