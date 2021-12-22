import { Component, OnInit } from '@angular/core';
import {LoginService} from "../Services/login-service.service";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";
import {switchMap, take} from "rxjs";
import {InformationService} from "../Services/information.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  isLoading: boolean = false;
  constructor( private logService: LoginService, private router: Router, private informationService:InformationService) { }

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

    this.logService.authenticateUser( user).pipe( take(1)).subscribe( {
        next: (data) => {
          this.userAuthenticated = true;

          const userData = JSON.parse(localStorage.getItem('userData'));

          this.informationService.getRoleInfo( userData.uuid, userData.role).pipe( take(1)).subscribe( () => {
            this.informationService.neighborStatus( userData.uuid).pipe( take( 1)).subscribe( () => {
              this.router.navigate( ['/personal-info']);
              this.isLoading = false;
            })
          });

          // this.informationService.getRoleInfo( userData.uuid, userData.role).pipe(
          //   switchMap( (data) => {
          //
          //   })
          // ).subscribe( () => {
          //   this.router.navigate( ['/personal-info']);
          //   this.isLoading = false;
          // });

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
