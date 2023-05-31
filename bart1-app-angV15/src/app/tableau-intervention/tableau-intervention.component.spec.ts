import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableauInterventionComponent } from './tableau-intervention.component';

describe('TableauInterventionComponent', () => {
  let component: TableauInterventionComponent;
  let fixture: ComponentFixture<TableauInterventionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TableauInterventionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TableauInterventionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
