import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, Observable, ReplaySubject, tap} from "rxjs";
import {User} from "./user.model";
import {Router} from "@angular/router";
import {HttpUrls} from "./HttpUrls";
import {InformationService} from "./information.service";
import {LocalStorageConstants} from "./LocalStorageConstants";

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  user = new BehaviorSubject<User>( null);

  constructor( private http: HttpClient, private router: Router) { }

  authenticateUser(incomingUser: {bilkentId: number, password: String}): Observable<any> {
    return this.http.post<any>( HttpUrls.baseUrl + "user/get", incomingUser)
      .pipe( tap( data => {
        // console.log( data);
        const newUser = new User( data.data.bilkentId,
          data.data.name,
          data.data.dateOfBirth,
          data.data.phoneNumber,
          data.data.age,
          data.data.id,
          data.data.role);
        this.user.next( newUser);
        //this.autoLogout(); CALL AUTOLOGOUT HERE
        localStorage.setItem(LocalStorageConstants.userData, JSON.stringify( newUser));
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
      uuid: string,
      role: string
    } = JSON.parse( localStorage.getItem( LocalStorageConstants.userData));
    if ( !userData) {
      return;
    }

    const loadedUser = new User( Number(userData.bilkentId),
      userData.name,
      new Date( userData.dateOfBirth),
      userData.phoneNumber,
      Number(userData.age),
      userData.uuid,
      userData.role);

    // CHECK IF USER TOKEN IS VALID?
    this.user.next( loadedUser);

  }
  logout() {
    this.user.next( null);
    localStorage.removeItem(LocalStorageConstants.userData);
    localStorage.removeItem( LocalStorageConstants.studentInfo);
    localStorage.removeItem( LocalStorageConstants.testResults);
    localStorage.removeItem( LocalStorageConstants.seating);
    localStorage.removeItem( LocalStorageConstants.generalInfo);
    localStorage.removeItem( LocalStorageConstants.guidelines);
    localStorage.removeItem( LocalStorageConstants.weeklyReports);
    localStorage.removeItem( LocalStorageConstants.announcement);
    localStorage.removeItem( LocalStorageConstants.violationReports);
    localStorage.removeItem( LocalStorageConstants.feedbackForms);
    localStorage.removeItem( LocalStorageConstants.requestForms);
    localStorage.removeItem( LocalStorageConstants.notifications);
    this.router.navigate(['/']);

    // if ( this.tokenExpirationTimer) {
    //   clearTimeout( this.tokenExpirationTimer);
    // }
    // this.tokenExpirationTimer = null;
  }

  // NEED TO CALL AUTOLOGOUT BUT NEED TOKEN
  private tokenExpirationTimer: any;
  autoLogout( expirationDuration: number) {
    this.tokenExpirationTimer = setTimeout( () => {
      this.logout();
    }, expirationDuration)
  }
}
