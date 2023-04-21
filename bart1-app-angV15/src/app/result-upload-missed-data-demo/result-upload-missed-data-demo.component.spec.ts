import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResultUploadMissedDataDemoComponent } from './result-upload-missed-data-demo.component';

describe('ResultUploadMissedDataDemoComponent', () => {
  let component: ResultUploadMissedDataDemoComponent;
  let fixture: ComponentFixture<ResultUploadMissedDataDemoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResultUploadMissedDataDemoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResultUploadMissedDataDemoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
