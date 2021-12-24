import { Injectable } from '@angular/core';
import {Observable, Subject, tap} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {HttpUrls} from "./HttpUrls";
import {LocalStorageConstants} from "./LocalStorageConstants";

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
    // get general info which includes cases, vacc percentage and announcements
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

  getPreviousViolationReports() {
    // get the user's uuid from local storage
    const uuid: string = JSON.parse(localStorage.getItem(LocalStorageConstants.userData)).uuid;
    // get the users's role from local storage
    const role = JSON.parse(localStorage.getItem(LocalStorageConstants.userData)).role;

    // if the role is admin, we retrieve all the reports else it retrieve only reports that were created by the user
    let link  = "";
    if ( role === "ADMIN")
    {
      link = HttpUrls.baseUrl + "violation_report/read";
    }
    else
    {
      link = HttpUrls.baseUrl + "violation_report/readAllFromUser/" + uuid;
    }

    // send http request according to user type
    return this.http.get<any>( link).
      pipe( tap( data => {
        console.log(data);
        if ( role === "ADMIN")
        {
          localStorage.setItem( LocalStorageConstants.violationReports, JSON.stringify( data));
        }
        else
        {
          localStorage.setItem( LocalStorageConstants.violationReports, JSON.stringify( data.data));
        }
      }));
  }

  getPreviousFeedbackForms() {
    // get the user's uuid from local storage
    const uuid: string = JSON.parse(localStorage.getItem(LocalStorageConstants.userData)).uuid;
    // get the users's role from local storage
    const role = JSON.parse(localStorage.getItem(LocalStorageConstants.userData)).role;

    // if the role is admin, we retrieve all the forms else it retrieves only forms that were created by the user
    let link  = "";
    if ( role === "ADMIN")
    {
      link = HttpUrls.baseUrl + "feedback_form/read";
    }
    else
    {
      link = HttpUrls.baseUrl + "feedback_form/readAllFromUser/" + uuid;
    }

    // if the role is admin, we retrieve all the forms else it retrieves only reports that were created by the user
    return this.http.get<any>( link).
      pipe( tap( data => {
        console.log(data);
        if ( role === "ADMIN")
        {
          localStorage.setItem( LocalStorageConstants.feedbackForms, JSON.stringify( data));
        }
        else
        {
          localStorage.setItem( LocalStorageConstants.feedbackForms, JSON.stringify( data.data));
        }
      }));
  }

  getPreviousRequestForms() {
    // get the user's uuid from local storage
    const uuid: string = JSON.parse(localStorage.getItem(LocalStorageConstants.userData)).uuid;
    // get the users's role from local storage
    const role = JSON.parse(localStorage.getItem(LocalStorageConstants.userData)).role;

    // if the role is admin, we retrieve all the forms else it retrieves only forms that were created by the user
    let link  = "";
    if ( role === "ADMIN")
    {
      link = HttpUrls.baseUrl + "request_form/read";
    }
    else
    {
      link = HttpUrls.baseUrl + "request_form/readAllFromUser/" + uuid;
    }

    // if the role is admin, we retrieve all the forms else it retrieves only reports that were created by the user
    return this.http.get<any>( link).
      pipe( tap( data => {
        console.log(data);
        if ( role === "ADMIN")
        {
          localStorage.setItem( LocalStorageConstants.requestForms, JSON.stringify( data));
        }
        else
        {
          localStorage.setItem( LocalStorageConstants.requestForms, JSON.stringify( data.data));
        }
      }));
  }
}
