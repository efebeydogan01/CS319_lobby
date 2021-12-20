import { Component, OnInit } from '@angular/core';
import {LoginService} from "../Services/login-service.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  constructor( private logService: LoginService, private router: Router) { }

  ngOnInit(): void {
  }

  loginUser( id: number, password: String) {
    let user = {
      "name": "efe",
      "id": "21901548",
      "password": password
    };
    this.logService.authenticateUser( user).subscribe(
      data => {
        console.log("ok");
        this.router.navigate( ['/personal-info']);
      }
    );
  }
}
