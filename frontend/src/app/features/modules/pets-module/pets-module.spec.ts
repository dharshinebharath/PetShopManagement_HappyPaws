import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetsModule } from './pets-module';

describe('PetsModule', () => {
  let component: PetsModule;
  let fixture: ComponentFixture<PetsModule>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PetsModule],
    }).compileComponents();

    fixture = TestBed.createComponent(PetsModule);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
