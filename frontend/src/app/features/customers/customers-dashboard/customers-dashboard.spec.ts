// This test file checks the behavior around the customers dashboard test flow.
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomersDashboard } from './customers-dashboard';

describe('CustomersDashboard', () => {
  let component: CustomersDashboard;
  let fixture: ComponentFixture<CustomersDashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CustomersDashboard],
    }).compileComponents();

    fixture = TestBed.createComponent(CustomersDashboard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
