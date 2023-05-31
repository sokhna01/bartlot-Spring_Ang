import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LandingPageInterventionComponent } from './landing-page-intervention.component';

describe('LandingPageInterventionComponent', () => {
  let component: LandingPageInterventionComponent;
  let fixture: ComponentFixture<LandingPageInterventionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LandingPageInterventionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LandingPageInterventionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
