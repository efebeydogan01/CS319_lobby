import {Component, OnInit} from '@angular/core';
import {LoginService} from "../Services/login-service.service";
import {LocalStorageConstants} from "../Services/LocalStorageConstants";

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.css']
})
export class TopbarComponent implements OnInit {
  userRole: string = JSON.parse( localStorage.getItem( LocalStorageConstants.userData)).role;
  role: string = "";
  roleIcon: string = "";

  constructor( private loginService: LoginService) { }

  ngOnInit(): void {
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

}
