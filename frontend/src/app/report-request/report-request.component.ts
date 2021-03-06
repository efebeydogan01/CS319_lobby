import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {LocalStorageConstants} from "../Services/LocalStorageConstants";
import {HttpClient} from "@angular/common/http";
import {HttpUrls} from "../Services/HttpUrls";
import {FormControl, NgForm, Validators} from "@angular/forms";

@Component({
  selector: 'app-report-request',
  templateUrl: './report-request.component.html',
  styleUrls: ['./report-request.component.css'],
  styles: [`
    .star {
      font-size: 1.5rem;
      color: #ced4da;
    }
    .filled {
      color: #cb0c9f;
    }
  `]
})
export class ReportRequestComponent implements OnInit {

  // @ViewChild('violationText') violationText: ElementRef;
  // @ViewChild('violationPlace') violationPlace: ElementRef;
  //
  // @ViewChild('requestTitle') requestTitle: ElementRef;
  // @ViewChild('requestBody') requestBody: ElementRef;
  //
  // @ViewChild('feedbackTitle') feedbackTitle: ElementRef;
  // @ViewChild('feedbackBody') feedbackBody: ElementRef;



  currentRate = 0;
  constructor( private http: HttpClient) { }

  ngOnInit(): void {
  }

  onViolationSubmit( violationText: string, violationPlace: string, form: NgForm) {
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
        form.reset();
      },
      error: () => {

        }
      }
    );
  }

  onRequestSubmit( requestTitle: string, requestBody: string, form: NgForm) {
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
          form.reset();
        },
        error: () => {

        }
      }
    );
  }

  onFeedbackSubmit( feedbackTitle: string, feedbackBody: string, form: NgForm) {
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
          this.currentRate = 0;
          form.reset();
        },
        error: () => {

        }
      }
    );
  }
}
