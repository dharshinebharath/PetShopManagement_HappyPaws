import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InventoryModule } from './inventory-module';

describe('InventoryModule', () => {
  let component: InventoryModule;
  let fixture: ComponentFixture<InventoryModule>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InventoryModule],
    }).compileComponents();

    fixture = TestBed.createComponent(InventoryModule);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
