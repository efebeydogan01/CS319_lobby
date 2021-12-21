import { Injectable } from '@angular/core';
import {LoginService} from "./login-service.service";
import {exhaustMap, take} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor( private loginService: LoginService, private http: HttpClient) { }

  getPersonalData() {
    // return this.loginService.user.pipe(
    //   take(1),
    //   exhaustMap( user => {
    //     return this.http.get(
    //       '',
    //       {
    //         params: new HttpParams().set('auth', user.id)
    //       }
    //     );
    //   })
    // );
    return this.loginService.user;
  }
}
