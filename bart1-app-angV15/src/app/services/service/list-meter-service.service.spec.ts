import { TestBed } from '@angular/core/testing';

import { ListMeterServiceService } from '../service/list-meter-service.service';

describe('ListMeterServiceService', () => {
  let service: ListMeterServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ListMeterServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
