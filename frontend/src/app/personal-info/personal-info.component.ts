import {Component, OnInit} from '@angular/core';
import {LoginService} from "../Services/login-service.service";
import {Subscription, take} from "rxjs";

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
    this.loginSubs = this.loginService.user.
    subscribe( {
      next: (data) => {
        console.log( data);
        localStorage.setItem('bilkentId', String(data.id));
        localStorage.setItem('name', data.name);
        // this.userName = data.name;
        // this.userId = data.id;
      },
      error: (err) => {
        console.log( "user not logged in!");
      }
    });

    this.userName = String( localStorage.getItem( 'name'));
    this.userId = Number(localStorage.getItem( 'bilkentId'));
  }


}
