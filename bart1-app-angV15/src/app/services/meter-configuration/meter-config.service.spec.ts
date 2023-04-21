import { TestBed } from '@angular/core/testing';

import { MeterConfigService } from './meter-config.service';

describe('MeterConfigService', () => {
  let service: MeterConfigService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MeterConfigService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
