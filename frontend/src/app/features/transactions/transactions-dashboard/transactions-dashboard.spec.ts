// This test file checks the behavior around the transactions dashboard test flow.
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransactionsDashboard } from './transactions-dashboard';

describe('TransactionsDashboard', () => {
  let component: TransactionsDashboard;
  let fixture: ComponentFixture<TransactionsDashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TransactionsDashboard],
    }).compileComponents();

    fixture = TestBed.createComponent(TransactionsDashboard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
