import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Task6Component } from './task6.component';

describe('Task6Component', () => {
  let component: Task6Component;
  let fixture: ComponentFixture<Task6Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Task6Component ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Task6Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
