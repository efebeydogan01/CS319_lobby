import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SendNotificationPopupComponent } from './send-notification-popup.component';

describe('SendNotificationPopupComponent', () => {
  let component: SendNotificationPopupComponent;
  let fixture: ComponentFixture<SendNotificationPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SendNotificationPopupComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SendNotificationPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
