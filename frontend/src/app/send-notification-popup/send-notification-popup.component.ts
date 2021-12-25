import { Component, OnInit } from '@angular/core';
import {LocalStorageConstants} from "../Services/LocalStorageConstants";
import {HttpClient} from "@angular/common/http";
import {HttpUrls} from "../Services/HttpUrls";
import {NgForm} from "@angular/forms";
import {InformationService} from "../Services/information.service";

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

  constructor( private http: HttpClient, private informationService: InformationService) { }

  ngOnInit(): void {
  }
  print(): void {
    console.log(this.toAll)
  }

  sendNotification( notificationTitle: string, notificationMessage: string, form: NgForm) {
    const uuid: string = JSON.parse( localStorage.getItem( LocalStorageConstants.userData)).uuid;
    const userData = {
      id: uuid
    };

    let notification = {
      user: userData,
      receivers: "",
      title: notificationTitle,
      message: notificationMessage
    }

    if ( this.toAll) {
      notification.receivers = "ALL";
      this.notificationRequest( notification, form);
    }
    else {
      if ( this.toStudent) {
        notification.receivers = "STUDENT";
      }
      else if ( this.toAcademic) {
        notification.receivers = "ACADEMIC_PERSONNEL";
      }
      else if ( this.toHealthCenter) {
        notification.receivers = "MEDICAL_EMPLOYEE";
      }
      else if ( this.toAdmins) {
        notification.receivers = "ADMIN";
      }
      this.notificationRequest( notification, form);
    }
  }

  notificationRequest( notification, form: NgForm) {
    this.http.post<any>( HttpUrls.baseUrl + "notification/create", notification).subscribe( {
        next: () => {
          console.log( "notification form is submitted");
          form.reset();
          this.toAll = false;
          this.toAdmins = false;
          this.toHealthCenter = false;
          this.toStudent = false;
          this.toAcademic = false;
          const user = JSON.parse( localStorage.getItem( LocalStorageConstants.userData));
          this.informationService.getNotifications( user.uuid).subscribe();
        },
        error: () => {
          console.log( "notification could not be submitted");
        }
      }
    );
  }
}
