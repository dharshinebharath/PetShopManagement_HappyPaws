import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroomingDashboard } from './grooming-dashboard';

describe('GroomingDashboard', () => {
  let component: GroomingDashboard;
  let fixture: ComponentFixture<GroomingDashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GroomingDashboard],
    }).compileComponents();

    fixture = TestBed.createComponent(GroomingDashboard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
