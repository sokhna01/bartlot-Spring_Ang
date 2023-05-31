import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopUpEditTacheHuitComponent } from './pop-up-edit-tache-huit.component';

describe('PopUpEditTacheHuitComponent', () => {
  let component: PopUpEditTacheHuitComponent;
  let fixture: ComponentFixture<PopUpEditTacheHuitComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopUpEditTacheHuitComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PopUpEditTacheHuitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
