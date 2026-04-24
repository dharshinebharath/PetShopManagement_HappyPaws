// This test file checks the behavior around the supplier dashboard test flow.
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupplierDashboard } from './supplier-dashboard';

describe('SupplierDashboard', () => {
  let component: SupplierDashboard;
  let fixture: ComponentFixture<SupplierDashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SupplierDashboard]
    }).compileComponents();

    fixture = TestBed.createComponent(SupplierDashboard);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
