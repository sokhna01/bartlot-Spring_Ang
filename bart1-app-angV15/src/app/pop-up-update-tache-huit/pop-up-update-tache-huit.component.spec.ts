import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopUpUpdateTacheHuitComponent } from './pop-up-update-tache-huit.component';

describe('PopUpUpdateTacheHuitComponent', () => {
  let component: PopUpUpdateTacheHuitComponent;
  let fixture: ComponentFixture<PopUpUpdateTacheHuitComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopUpUpdateTacheHuitComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PopUpUpdateTacheHuitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
