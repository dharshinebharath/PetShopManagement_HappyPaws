// This test file checks the behavior around the grooming form test flow.
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroomingForm } from './grooming-form';

describe('GroomingForm', () => {
  let component: GroomingForm;
  let fixture: ComponentFixture<GroomingForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GroomingForm],
    }).compileComponents();

    fixture = TestBed.createComponent(GroomingForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
