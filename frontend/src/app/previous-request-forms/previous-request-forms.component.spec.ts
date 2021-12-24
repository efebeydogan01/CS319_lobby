import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreviousRequestFormsComponent } from './previous-request-forms.component';

describe('PreviousRequestFormsComponent', () => {
  let component: PreviousRequestFormsComponent;
  let fixture: ComponentFixture<PreviousRequestFormsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PreviousRequestFormsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PreviousRequestFormsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
