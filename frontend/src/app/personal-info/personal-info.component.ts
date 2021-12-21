import { Component, OnInit } from '@angular/core';
import {LoginService} from "../Services/login-service.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-personal-info',
  templateUrl: './personal-info.component.html',
  styleUrls: ['./personal-info.component.css']
})
export class PersonalInfoComponent implements OnInit {
  loginSubs: Subscription = new Subscription();
  constructor( private loginService: LoginService) { }
  userName: string = "";
  userId: number = 0;
  ngOnInit(): void {
    this.loginSubs = this.loginService.user.subscribe( data => {
      this.userName = data.name;
      this.userId = data.id;
    });
  }


}
