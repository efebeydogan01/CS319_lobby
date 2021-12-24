import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreviousFeedbackFormsComponent } from './previous-feedback-forms.component';

describe('PreviousFeedbackFormsComponent', () => {
  let component: PreviousFeedbackFormsComponent;
  let fixture: ComponentFixture<PreviousFeedbackFormsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PreviousFeedbackFormsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PreviousFeedbackFormsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
