// This test file checks the behavior around the pet supplier list test flow.
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetSupplierList } from './pet-supplier-list';

describe('PetSupplierList', () => {
  let component: PetSupplierList;
  let fixture: ComponentFixture<PetSupplierList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PetSupplierList],
    }).compileComponents();

    fixture = TestBed.createComponent(PetSupplierList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
