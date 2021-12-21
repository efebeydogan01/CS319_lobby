import { Component, OnInit } from '@angular/core';
import {LoginService} from "../Services/login-service.service";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  isLoading: boolean = false;
  constructor( private logService: LoginService, private router: Router) { }

  ngOnInit(): void {
    if ( localStorage.getItem( 'userData')) {
      this.router.navigate( ['/personal-info']);
    }
  }
  userAuthenticated: boolean = true;
  loginUser( id: number, password: String, form: NgForm) {
    let user = {
      "bilkentId": id,
      "password": password
    };

    this.isLoading = true;

    this.logService.authenticateUser( user).subscribe( {
        next: (data) => {
          this.userAuthenticated = true;
          this.router.navigate( ['/personal-info']);
          this.isLoading = false;
        },
        error: (err) => {
          this.userAuthenticated = false;
          console.log( "User cannot be authenticated");
          this.isLoading = false;
        }
      }
    );
    form.reset();
  }
}
