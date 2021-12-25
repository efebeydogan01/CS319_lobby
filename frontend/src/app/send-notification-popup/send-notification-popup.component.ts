import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-send-notification-popup',
  templateUrl: './send-notification-popup.component.html',
  styleUrls: ['./send-notification-popup.component.css']
})
export class SendNotificationPopupComponent implements OnInit {
  toAll: boolean = false;
  toStudent: boolean = false;
  toHealthCenter: boolean = false;
  toAcademic: boolean = false;
  toAdmins: boolean = false;

  constructor() { }

  ngOnInit(): void {
  }
  print(): void {
    console.log(this.toAll)
  }

  sendNotification( notificationTitle: string, notificationMessage: string) {
    notification: {

    }
    if ( this.toAll) {

    }
  }
}
