import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {LoginService} from "../Services/login-service.service";
import {Subscription, take} from "rxjs";
import {InformationService} from "../Services/information.service";
import {LocalStorageConstants} from "../Services/LocalStorageConstants";
import {HttpClient} from "@angular/common/http";
import {HttpUrls} from "../Services/HttpUrls";

@Component({
  selector: 'app-personal-info',
  templateUrl: './personal-info.component.html',
  styleUrls: ['./personal-info.component.css']
})
export class PersonalInfoComponent implements OnInit {

  testResults: {
    id: string,
    result: string,
    testDate: string,
    type: string
  }[] = null;

  covidStatus: string = "";

  studentInfo: {
    department: string,
    year: string
  } = null;

  userData: {
    id: number,
    name: string,
    dob: string,
    phoneNumber: string,
    age: number,
    uuid: string,
    role: string,
    neighborStatus: string
  } = null;

  @ViewChild('vaccFile')
  uploadedFile: ElementRef;

  formData: FormData;

  constructor( private loginService: LoginService, private informationService: InformationService, private http: HttpClient) { }

  ngOnInit(): void {
    const localUser = localStorage.getItem(LocalStorageConstants.userData);
    if ( localUser) {
      this.userData = JSON.parse(localUser);
      this.userData.dob = this.userData.dob.substring(0, 10);
      this.informationService.getCovidInfo( this.userData.uuid).pipe( take(1)).subscribe( {
        next: (data) => {
          this.covidStatus = data.data.status;
        }
      });

      if ( localStorage.getItem(LocalStorageConstants.studentInfo)) {
        if ( this.userData.role === 'STUDENT')
          this.studentInfo = JSON.parse( localStorage.getItem(LocalStorageConstants.studentInfo));
      }

      const tests = JSON.parse( localStorage.getItem( LocalStorageConstants.testResults));
      if ( tests) {
        this.testResults = tests;
      }

    }
  }
  onFileChange( event) {
    const file: File = event.target.files[0];
    console.log( file);
    if ( file) {
      this.formData = new FormData();
      this.formData.append( "file", file);
    }
  }

  removeFile() {
    this.uploadedFile.nativeElement.value = "";
  }

  onUpload() {
    const upload$ = this.http.post( HttpUrls.baseUrl + "vaccine/uploadFile/" + this.userData.uuid, this.formData);
    upload$.pipe( take(1)).subscribe( {
      next: (data: any) => {
      },
      error: () => {
        console.log( "File could not be uploaded");
      }
    });
  }

}
