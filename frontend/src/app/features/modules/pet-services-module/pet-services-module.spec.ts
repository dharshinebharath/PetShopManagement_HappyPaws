import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetServicesModule } from './pet-services-module';

describe('PetServicesModule', () => {
  let component: PetServicesModule;
  let fixture: ComponentFixture<PetServicesModule>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PetServicesModule],
    }).compileComponents();

    fixture = TestBed.createComponent(PetServicesModule);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
