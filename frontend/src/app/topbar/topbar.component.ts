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

  constructor( private loginService: LoginService) { }

  ngOnInit(): void {
  }


  onLogout() {
    this.loginService.logout();
  }

}
