import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResultUpdateQualiteDemoComponent } from './result-update-qualite-demo.component';

describe('ResultUpdateQualiteDemoComponent', () => {
  let component: ResultUpdateQualiteDemoComponent;
  let fixture: ComponentFixture<ResultUpdateQualiteDemoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResultUpdateQualiteDemoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResultUpdateQualiteDemoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
