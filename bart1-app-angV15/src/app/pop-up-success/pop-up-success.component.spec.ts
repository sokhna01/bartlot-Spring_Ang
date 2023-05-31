import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopUpSuccessComponent } from './pop-up-success.component';

describe('PopUpSuccessComponent', () => {
  let component: PopUpSuccessComponent;
  let fixture: ComponentFixture<PopUpSuccessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopUpSuccessComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PopUpSuccessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
