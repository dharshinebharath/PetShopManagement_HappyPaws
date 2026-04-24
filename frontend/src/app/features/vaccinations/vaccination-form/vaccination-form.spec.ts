// This test file checks the behavior around the vaccination form test flow.
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VaccinationForm } from './vaccination-form';

describe('VaccinationForm', () => {
  let component: VaccinationForm;
  let fixture: ComponentFixture<VaccinationForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VaccinationForm],
    }).compileComponents();

    fixture = TestBed.createComponent(VaccinationForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
