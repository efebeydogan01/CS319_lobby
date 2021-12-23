import { Injectable } from '@angular/core';
import {Observable, Subject, tap} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {HttpUrls} from "./HttpUrls";
import {LocalStorageConstants} from "./LocalStorageConstants";
import {User} from "./user.model";

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
        localStorage.setItem( LocalStorageConstants.studentInfo, JSON.stringify( studentInfo));
      }
    }));
  }

  neighborStatus( uuid: string): Observable<any> {
    return this.http.get<any>( HttpUrls.baseUrl + "neighbor/getRiskStatus/" + uuid).
      pipe( tap( data => {
        let newUserData = JSON.parse( localStorage.getItem( LocalStorageConstants.userData));
        newUserData.neighborStatus = data.data;
        localStorage.setItem( LocalStorageConstants.userData, JSON.stringify( newUserData));
        // console.log( JSON.parse( localStorage.getItem(LocalStorageConstants.userData)).neighborStatus);
      }));
  }

  getTestResults( uuid: string) {
    return this.http.get<any>( HttpUrls.baseUrl + "test_result/getTestResults/" + uuid).
      pipe( tap( data => {
        localStorage.setItem( LocalStorageConstants.testResults, JSON.stringify( data.data));
    }));
  }

  getSeatingPlan( section: {courseName: string, sectionNo: number}) {
    return this.http.post<any>( HttpUrls.baseUrl + "section/seating", section).
      pipe( tap( data => {
        console.log(data);
        localStorage.setItem( LocalStorageConstants.seating, JSON.stringify( data.data));
      }));
  }

  getGeneralInfo() {
    return this.http.get<any>( HttpUrls.baseUrl + "announcement/readGeneralInfo").
      pipe( tap( data => {
        console.log(data);
        localStorage.setItem( LocalStorageConstants.generalInfo, JSON.stringify( data.data));
      }));
  }

  getGuidelines() {
    return this.http.get<any>( HttpUrls.baseUrl + "guidelines").
      pipe( tap( data => {
        console.log(data);
        localStorage.setItem( LocalStorageConstants.guidelines, JSON.stringify( data.data));
      }));
  }

  getWeeklyReports() {
    return this.http.get<any>( HttpUrls.baseUrl + "weeklyReports").
      pipe( tap( data => {
        console.log(data);
        localStorage.setItem( LocalStorageConstants.guidelines, JSON.stringify( data.data));
      }));
  }

  makeAnnouncement( announcement: { title: string, date:string, announcementText: string}) {
    return this.http.post<any>( HttpUrls.baseUrl + "announcement/create", announcement);
  }
}
