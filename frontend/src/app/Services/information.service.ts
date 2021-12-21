import { Injectable } from '@angular/core';
import {Observable, Subject, tap} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {HttpUrls} from "./HttpUrls";

@Injectable({
  providedIn: 'root'
})
export class InformationService {
  roleInfo: Subject<string> = new Subject();
  constructor( private http: HttpClient) { }

  getCovidInfo( uuid: string): Observable<any> {
    return this.http.get<any>( HttpUrls.baseUrl + "covid/get/" + uuid).
      pipe( tap( data => {
    }));
  }

  getRoleInfo( uuid: string, role: string): Observable<any> {
    return this.http.get<any>( HttpUrls.baseUrl + role.toLowerCase() + "/read/" + uuid).
    pipe( tap( data => {
    }));
  }

}
