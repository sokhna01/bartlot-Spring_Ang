import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableConfigurationPointsDeCompteurComponent } from './table-configuration-points-de-compteur.component';

describe('TableConfigurationPointsDeCompteurComponent', () => {
  let component: TableConfigurationPointsDeCompteurComponent;
  let fixture: ComponentFixture<TableConfigurationPointsDeCompteurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TableConfigurationPointsDeCompteurComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TableConfigurationPointsDeCompteurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
