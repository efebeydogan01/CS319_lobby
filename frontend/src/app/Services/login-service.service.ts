import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, ReplaySubject, tap} from "rxjs";
import {User} from "./user.model";

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  user = new ReplaySubject<User>( 1);

  constructor( private http: HttpClient) { }

  authenticateUser(incomingUser: {bilkentId: number, password: String}): Observable<any> {
    return this.http.post<any>( "http://localhost:8080/user/get", incomingUser)
      .pipe( tap( data => {
        const newUser = new User( data.data.bilkentId, data.data.name);
        console.log( data);
        this.user.next( newUser);
      })
    );
  }
}
