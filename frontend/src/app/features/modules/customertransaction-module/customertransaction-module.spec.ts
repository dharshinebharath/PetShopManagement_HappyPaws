import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomertransactionModule } from './customertransaction-module';

describe('CustomertransactionModule', () => {
  let component: CustomertransactionModule;
  let fixture: ComponentFixture<CustomertransactionModule>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CustomertransactionModule],
    }).compileComponents();

    fixture = TestBed.createComponent(CustomertransactionModule);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
