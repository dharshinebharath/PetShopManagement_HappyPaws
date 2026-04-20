import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroomingList } from './grooming-list';

describe('GroomingList', () => {
  let component: GroomingList;
  let fixture: ComponentFixture<GroomingList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GroomingList],
    }).compileComponents();

    fixture = TestBed.createComponent(GroomingList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
