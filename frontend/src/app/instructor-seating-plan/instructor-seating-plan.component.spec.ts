import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstructorSeatingPlanComponent } from './instructor-seating-plan.component';

describe('InstructorSeatingPlanComponent', () => {
  let component: InstructorSeatingPlanComponent;
  let fixture: ComponentFixture<InstructorSeatingPlanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InstructorSeatingPlanComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InstructorSeatingPlanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
