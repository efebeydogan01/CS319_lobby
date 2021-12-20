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
  userAuthenticated: boolean = true;
  loginUser( id: number, password: String) {
    let user = {
      "bilkentId": id,
      "password": password
    };
    this.logService.authenticateUser( user).subscribe( {
        next: (data) => {
          this.userAuthenticated = true;
          this.router.navigate( ['/personal-info']);
        },
        error: (err) => {
          this.userAuthenticated = false;
          console.log( "User cannot be authenticated: " + err);
        }
      }
    );
  }
}
