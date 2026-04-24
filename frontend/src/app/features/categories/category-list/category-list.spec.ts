// This test file checks the behavior around the category list test flow.
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoryList } from './category-list';

describe('CategoryList', () => {
  let component: CategoryList;
  let fixture: ComponentFixture<CategoryList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CategoryList],
    }).compileComponents();

    fixture = TestBed.createComponent(CategoryList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
