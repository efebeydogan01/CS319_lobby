import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, Subject, tap} from "rxjs";
import {User} from "./user.model";

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  user = new Subject<User>();

  constructor( private http: HttpClient) { }

  authenticateUser(user: {bilkentId: number, password: String}): Observable<any> {
    // return this.http.post<any>( "http://localhost:8080/user/create", user)
    //   .pipe( tap( data => {
    //     const expDate = new Date(new Date().getTime() + +data.tokenExpDate * 1000);
    //
    //     const user = new User( data.id, data.token, expDate);
    //     this.user.next( user);
    //   })
    // );

    return this.http.post<any>( "http://localhost:8080/user/create", user);
  }
}
