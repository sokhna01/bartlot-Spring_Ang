import { TestBed } from '@angular/core/testing';

import { CreateHighChartService } from './create-high-chart.service';

describe('CreateHighChartService', () => {
  let service: CreateHighChartService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateHighChartService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
