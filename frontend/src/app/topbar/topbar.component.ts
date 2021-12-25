import {Component, OnInit} from '@angular/core';
import {LoginService} from "../Services/login-service.service";
import {LocalStorageConstants} from "../Services/LocalStorageConstants";
import {HttpClient} from "@angular/common/http";
import {HttpUrls} from "../Services/HttpUrls";
import {InformationService} from "../Services/information.service";

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.css']
})
export class TopbarComponent implements OnInit {
  userRole: string = JSON.parse( localStorage.getItem( LocalStorageConstants.userData)).role;
  role: string = "";
  roleIcon: string = "";

  notifications: {
    id: number,
    message: string,
    title: string
  }[] = null;

  constructor( private loginService: LoginService, private http: HttpClient, private informationService:InformationService) { }

  ngOnInit(): void {
    const notificationsArray = JSON.parse( localStorage.getItem(LocalStorageConstants.notifications));
    if ( notificationsArray) {
      this.notifications = notificationsArray;
    }
    switch ( this.userRole) {
      case ( 'STUDENT'):
        this.role = "Student";
        this.roleIcon = "fa-user-graduate";
        break;
      case( 'ADMIN'):
        this.role = "Admin";
        this.roleIcon = "fa-users-cog";
        break;
      case( 'MEDICAL_EMPLOYEE'):
        this.role = "Medical Employee";
        this.roleIcon = "fa-user-nurse";
        break;
      case( 'ACADEMIC_PERSONNEL'):
        this.role = "Academic Personnel";
        this.roleIcon = "fa-chalkboard-teacher";
        break;
    }
  }


  onLogout() {
    this.loginService.logout();
  }

  deleteNotification( index: number) {
    this.http.delete<any>( HttpUrls.baseUrl + "notification/delete/" + this.notifications[index].id).subscribe( () => {
      this.informationService.getNotifications( (JSON.parse( localStorage.getItem( LocalStorageConstants.userData))).uuid).subscribe( () => {
        window.location.reload();
      });
    });
  }

}
