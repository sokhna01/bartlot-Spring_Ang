import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopUpInterventionComponent } from './pop-up-intervention.component';

describe('PopUpInterventionComponent', () => {
  let component: PopUpInterventionComponent;
  let fixture: ComponentFixture<PopUpInterventionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopUpInterventionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PopUpInterventionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
