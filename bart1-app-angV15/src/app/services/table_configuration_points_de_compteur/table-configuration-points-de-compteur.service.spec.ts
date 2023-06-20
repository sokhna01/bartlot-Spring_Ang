import { TestBed } from '@angular/core/testing';

import { TableConfigurationPointsDeCompteurService } from './table-configuration-points-de-compteur.service';

describe('TableConfigurationPointsDeCompteurService', () => {
  let service: TableConfigurationPointsDeCompteurService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TableConfigurationPointsDeCompteurService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
