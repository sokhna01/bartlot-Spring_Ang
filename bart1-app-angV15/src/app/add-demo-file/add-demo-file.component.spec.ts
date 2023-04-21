import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDemoFileComponent } from './add-demo-file.component';

describe('AddDemoFileComponent', () => {
  let component: AddDemoFileComponent;
  let fixture: ComponentFixture<AddDemoFileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddDemoFileComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddDemoFileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
