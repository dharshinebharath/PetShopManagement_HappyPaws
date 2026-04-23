import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetSupplierDashboard } from './pet-supplier-dashboard';

describe('PetSupplierDashboard', () => {
  let component: PetSupplierDashboard;
  let fixture: ComponentFixture<PetSupplierDashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PetSupplierDashboard],
    }).compileComponents();

    fixture = TestBed.createComponent(PetSupplierDashboard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
