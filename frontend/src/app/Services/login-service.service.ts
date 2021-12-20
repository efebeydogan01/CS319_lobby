import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  readonly rootUrl: String = '';
  constructor( private http: HttpClient) { }

  // authenticateUser(user: {id: number, password: String}): Observable<any> {
  //   return this.http.post<any>( "/user/create", user);
  // }

  authenticateUser(user: {name: String, id: String, password: String}): Observable<any> {
    return this.http.post<any>( "http://localhost:8080/user/create", user);
  }
}
