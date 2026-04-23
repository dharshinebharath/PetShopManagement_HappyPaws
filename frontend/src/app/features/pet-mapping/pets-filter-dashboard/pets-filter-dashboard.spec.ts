import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetsFilterDashboard } from './pets-filter-dashboard';

describe('PetsFilterDashboard', () => {
  let component: PetsFilterDashboard;
  let fixture: ComponentFixture<PetsFilterDashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PetsFilterDashboard],
    }).compileComponents();

    fixture = TestBed.createComponent(PetsFilterDashboard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
