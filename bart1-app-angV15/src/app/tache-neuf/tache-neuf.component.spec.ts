import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TacheNeufComponent } from './tache-neuf.component';

describe('TacheNeufComponent', () => {
  let component: TacheNeufComponent;
  let fixture: ComponentFixture<TacheNeufComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TacheNeufComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TacheNeufComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
