import {Component, OnDestroy, OnInit} from '@angular/core';
import {LoginService} from "../Services/login-service.service";
import {InformationService} from "../Services/information.service";

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.css']
})
export class TopbarComponent implements OnInit {
  userRole: string = JSON.parse( localStorage.getItem( 'userData')).role;
  constructor( private loginService: LoginService, private informationService: InformationService) { }

  ngOnInit(): void {
  }


  onLogout() {
    this.loginService.logout();
  }

}
