import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopUpHorodatageComponent } from './pop-up-horodatage.component';

describe('PopUpHorodatageComponent', () => {
  let component: PopUpHorodatageComponent;
  let fixture: ComponentFixture<PopUpHorodatageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopUpHorodatageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PopUpHorodatageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
