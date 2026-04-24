// This test file checks the behavior around the pagination test flow.
import { TestBed } from '@angular/core/testing';

import { Pagination } from './pagination';

describe('Pagination', () => {
  let service: Pagination;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Pagination);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
