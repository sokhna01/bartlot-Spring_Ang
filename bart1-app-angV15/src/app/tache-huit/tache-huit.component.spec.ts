import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TacheHuitComponent } from './tache-huit.component';

describe('TacheHuitComponent', () => {
  let component: TacheHuitComponent;
  let fixture: ComponentFixture<TacheHuitComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TacheHuitComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TacheHuitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
