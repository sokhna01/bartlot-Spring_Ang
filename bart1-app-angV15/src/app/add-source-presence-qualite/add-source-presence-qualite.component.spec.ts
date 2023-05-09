import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSourcePresenceQualiteComponent } from './add-source-presence-qualite.component';

describe('AddSourcePresenceQualiteComponent', () => {
  let component: AddSourcePresenceQualiteComponent;
  let fixture: ComponentFixture<AddSourcePresenceQualiteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddSourcePresenceQualiteComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddSourcePresenceQualiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
