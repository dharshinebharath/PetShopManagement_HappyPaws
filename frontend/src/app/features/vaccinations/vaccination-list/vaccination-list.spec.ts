import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VaccinationList } from './vaccination-list';

describe('VaccinationList', () => {
  let component: VaccinationList;
  let fixture: ComponentFixture<VaccinationList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VaccinationList],
    }).compileComponents();

    fixture = TestBed.createComponent(VaccinationList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
