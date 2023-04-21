import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChartDemoComponent } from './chart-demo.component';

describe('ChartDemoComponent', () => {
  let component: ChartDemoComponent;
  let fixture: ComponentFixture<ChartDemoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChartDemoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChartDemoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
