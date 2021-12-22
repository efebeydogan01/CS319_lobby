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
      if ( role === 'STUDENT') {
        const studentInfo: {
          department: string,
          year: string
        } = { department: data.data.department, year: data.data.year};
        localStorage.setItem( 'studentInfo', JSON.stringify( studentInfo));
      }
    }));
  }

  neighborStatus( uuid: string): Observable<any> {
    return this.http.get<any>( HttpUrls.baseUrl + "neighbor/getRiskStatus/" + uuid).
      pipe( tap( data => {
        let newUserData = JSON.parse( localStorage.getItem( 'userData'));
        newUserData.neighborStatus = data.data;
        localStorage.setItem( 'userData', JSON.stringify( newUserData));
        // console.log( JSON.parse( localStorage.getItem('userData')).neighborStatus);
      }));
  }

}
