import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResultUpdateSourceDemoComponent } from './result-update-source-demo.component';

describe('ResultUpdateSourceDemoComponent', () => {
  let component: ResultUpdateSourceDemoComponent;
  let fixture: ComponentFixture<ResultUpdateSourceDemoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResultUpdateSourceDemoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResultUpdateSourceDemoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
