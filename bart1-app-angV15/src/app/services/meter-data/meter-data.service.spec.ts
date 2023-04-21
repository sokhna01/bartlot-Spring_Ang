import { TestBed } from '@angular/core/testing';

import { MeterDataService } from './meter-data.service';

describe('MeterDataService', () => {
  let service: MeterDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MeterDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
