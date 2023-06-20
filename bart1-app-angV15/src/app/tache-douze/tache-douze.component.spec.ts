import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TacheDouzeComponent } from './tache-douze.component';

describe('TacheDouzeComponent', () => {
  let component: TacheDouzeComponent;
  let fixture: ComponentFixture<TacheDouzeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TacheDouzeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TacheDouzeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
