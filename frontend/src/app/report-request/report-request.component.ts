import { Component, OnInit } from '@angular/core';
import {LocalStorageConstants} from "../Services/LocalStorageConstants";
import {HttpClient} from "@angular/common/http";
import {HttpUrls} from "../Services/HttpUrls";
import {FormControl, Validators} from "@angular/forms";

@Component({
  selector: 'app-report-request',
  templateUrl: './report-request.component.html',
  styleUrls: ['./report-request.component.css'],
  styles: [`
    .star {
      font-size: 1.5rem;
      color: #b0c4de;
    }
    .filled {
      color: #1e90ff;
    }
    .bad {
      color: #deb0b0;
    }
    .filled.bad {
      color: #ff1e1e;
    }
  `]
})
export class ReportRequestComponent implements OnInit {
  currentRate = 0;
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

    this.http.post<any>( HttpUrls.baseUrl + "violation_report/create", violation).subscribe( {
      next: () => {
        console.log( "violation report is submitted");
      },
      error: () => {

        }
      }
    );
  }

  onRequestSubmit( requestTitle: string, requestBody: string) {
    const uuid: string = JSON.parse( localStorage.getItem( LocalStorageConstants.userData)).uuid;
    const userData = {
      id: uuid
    };

    const request = {
      user: userData,
      title: requestTitle,
      request: requestBody
    };

    this.http.post<any>( HttpUrls.baseUrl + "request_form/create", request).subscribe( {
        next: () => {
          console.log( "request form is submitted");
        },
        error: () => {

        }
      }
    );
  }

  onFeedbackSubmit( feedbackTitle: string, feedbackBody: string) {
    const uuid: string = JSON.parse( localStorage.getItem( LocalStorageConstants.userData)).uuid;
    const userData = {
      id: uuid
    };

    const feedback = {
      user: userData,
      rating: this.currentRate,
      title: feedbackTitle,
      feedback: feedbackBody
    };

    this.http.post<any>( HttpUrls.baseUrl + "feedback_form/create", feedback).subscribe( {
        next: () => {
          console.log( "feedback form is submitted");
        },
        error: () => {

        }
      }
    );

  }
}
