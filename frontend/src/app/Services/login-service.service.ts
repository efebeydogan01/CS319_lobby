import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, Observable, ReplaySubject, tap} from "rxjs";
import {User} from "./user.model";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  user = new BehaviorSubject<User>( null);

  constructor( private http: HttpClient, private router: Router) { }

  authenticateUser(incomingUser: {bilkentId: number, password: String}): Observable<any> {
    return this.http.post<any>( "http://localhost:8080/user/get", incomingUser)
      .pipe( tap( data => {
        const newUser = new User( data.data.bilkentId, data.data.name);
        console.log( data);
        this.user.next( newUser);
      })
    );
  }

  logout() {
    this.user.next( null);
    localStorage.clear();
    this.router.navigate(['/']);
  }
}
