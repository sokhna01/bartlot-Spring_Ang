import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResultUploadFileDemoComponent } from './result-upload-file-demo.component';

describe('ResultUploadFileDemoComponent', () => {
  let component: ResultUploadFileDemoComponent;
  let fixture: ComponentFixture<ResultUploadFileDemoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResultUploadFileDemoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResultUploadFileDemoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
