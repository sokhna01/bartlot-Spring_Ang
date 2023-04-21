import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChartDemoLineComponent } from './chart-demo-line.component';

describe('ChartDemoLineComponent', () => {
  let component: ChartDemoLineComponent;
  let fixture: ComponentFixture<ChartDemoLineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChartDemoLineComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChartDemoLineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
