import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddExternalSourceComponent } from './add-external-source.component';

describe('AddExternalSourceComponent', () => {
  let component: AddExternalSourceComponent;
  let fixture: ComponentFixture<AddExternalSourceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddExternalSourceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddExternalSourceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
