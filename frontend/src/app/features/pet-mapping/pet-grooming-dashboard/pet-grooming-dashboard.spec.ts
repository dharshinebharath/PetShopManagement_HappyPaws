// This test file checks the behavior around the pet grooming dashboard test flow.
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetGroomingDashboard } from './pet-grooming-dashboard';

describe('PetGroomingDashboard', () => {
  let component: PetGroomingDashboard;
  let fixture: ComponentFixture<PetGroomingDashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PetGroomingDashboard],
    }).compileComponents();

    fixture = TestBed.createComponent(PetGroomingDashboard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
