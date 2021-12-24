import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreviousViolationReportsComponent } from './previous-violation-reports.component';

describe('PreviousViolationReportsComponent', () => {
  let component: PreviousViolationReportsComponent;
  let fixture: ComponentFixture<PreviousViolationReportsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PreviousViolationReportsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PreviousViolationReportsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
