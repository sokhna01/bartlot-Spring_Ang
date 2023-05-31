import { TestBed } from '@angular/core/testing';

import { WorkTableServiceService } from './work-table-service.service';

describe('WorkTableServiceService', () => {
  let service: WorkTableServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WorkTableServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
