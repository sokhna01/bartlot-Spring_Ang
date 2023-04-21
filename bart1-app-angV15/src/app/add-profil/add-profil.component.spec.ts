import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddProfilComponent } from './add-profil.component';

describe('AddProfilComponent', () => {
  let component: AddProfilComponent;
  let fixture: ComponentFixture<AddProfilComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddProfilComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddProfilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
