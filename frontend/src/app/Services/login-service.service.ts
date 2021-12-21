import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, Observable, ReplaySubject, tap} from "rxjs";
import {User} from "./user.model";
import {Router} from "@angular/router";
import {HttpUrls} from "./HttpUrls";

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  user = new BehaviorSubject<User>( null);

  constructor( private http: HttpClient, private router: Router) { }

  authenticateUser(incomingUser: {bilkentId: number, password: String}): Observable<any> {
    return this.http.post<any>( HttpUrls.baseUrl + "user/get", incomingUser)
      .pipe( tap( data => {
        console.log( data);
        const newUser = new User( data.data.bilkentId,
          data.data.name,
          data.data.dateOfBirth,
          data.data.phoneNumber,
          data.data.age,
          data.data.department,
          data.data.year,
          data.data.id);
        this.user.next( newUser);
        //this.autoLogout(); CALL AUTOLOGOUT HERE
        localStorage.setItem('userData', JSON.stringify( newUser));
      })
    );
  }

  autoLogin() {
    const userData: {
      bilkentId: string,
      name: string,
      dateOfBirth: string,
      phoneNumber: string,
      age: string,
      department: string,
      year: string,
      uuid: string
    } = JSON.parse( localStorage.getItem( 'userData'));
    if ( !userData) {
      return;
    }

    const loadedUser = new User( Number(userData.bilkentId),
      userData.name,
      new Date( userData.dateOfBirth),
      userData.phoneNumber,
      Number(userData.age),
      userData.department,
      Number(userData.year),
      userData.uuid);

    // CHECK IF USER TOKEN IS VALID?
    this.user.next( loadedUser);

  }
  logout() {
    this.user.next( null);
    localStorage.removeItem('userData');
    this.router.navigate(['/']);

    if ( this.tokenExpirationTimer) {
      clearTimeout( this.tokenExpirationTimer);
    }
    this.tokenExpirationTimer = null;
  }

  // NEED TO CALL AUTOLOGOUT BUT NEED TOKEN
  private tokenExpirationTimer: any;
  autoLogout( expirationDuration: number) {
    this.tokenExpirationTimer = setTimeout( () => {
      this.logout();
    }, expirationDuration)
  }
}
