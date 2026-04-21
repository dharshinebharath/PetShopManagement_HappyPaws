import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetsDashboard } from './pets-dashboard';

describe('PetsDashboard', () => {
  let component: PetsDashboard;
  let fixture: ComponentFixture<PetsDashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PetsDashboard],
    }).compileComponents();

    fixture = TestBed.createComponent(PetsDashboard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
