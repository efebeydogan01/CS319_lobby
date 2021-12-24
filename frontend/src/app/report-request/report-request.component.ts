import { Component, OnInit } from '@angular/core';
import {LocalStorageConstants} from "../Services/LocalStorageConstants";
import {HttpClient} from "@angular/common/http";
import {HttpUrls} from "../Services/HttpUrls";

@Component({
  selector: 'app-report-request',
  templateUrl: './report-request.component.html',
  styleUrls: ['./report-request.component.css']
})
export class ReportRequestComponent implements OnInit {

  constructor( private http: HttpClient) { }

  ngOnInit(): void {
  }

  onViolationSubmit( violationText: string, violationPlace: string) {
    const uuid: string = JSON.parse( localStorage.getItem( LocalStorageConstants.userData)).uuid;
    const userData = {
      id: uuid
    };
    const violation = {
      user: userData,
      message: violationText,
      place: violationPlace
    };

    this.http.post<any>( HttpUrls.baseUrl + "violation_report/create", violation).subscribe( () => {
      console.log( "violation report is submitted");
    });
  }
}
