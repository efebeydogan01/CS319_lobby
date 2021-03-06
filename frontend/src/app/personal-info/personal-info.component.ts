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

  vaccInfo: {
    vaccineName: string,
    date: string
  } [] = null;

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

      this.informationService.getTestResults(this.userData.uuid).subscribe( () => {
        this.testResults = JSON.parse( localStorage.getItem( LocalStorageConstants.testResults));
      });

      this.informationService.getVaccInfo(this.userData.uuid).subscribe( () => {
        this.vaccInfo = JSON.parse( localStorage.getItem( LocalStorageConstants.vaccinationInfo));
      });

    }
  }
  onFileChange( event) {
    const file: File = event.target.files[0];
    if ( file) {
      this.formData = new FormData();
      this.formData.append( "file", file);
    }
  }

  removeFile() {
    this.uploadedFile.nativeElement.value = "";
  }

  onUpload() {
    // const upload$ = this.http.post( HttpUrls.baseUrl + "file/upload/" + this.userData.uuid, this.formData);
    // upload$.pipe( take(1)).subscribe( {
    //   next: (data: any) => {
    //     console.log( data);
    //
    //     this.http.get( HttpUrls.baseUrl + "file/files/" + this.userData.uuid + ".pdf").subscribe( {
    //       next: (data) => {
    //         console.log( data);
    //       },
    //       error: () => {
    //         console.log( "file could not be retrieved");
    //       }
    //     });
    //     console.log( "vacc file uploaded");
    //   },
    //   error: () => {
    //     console.log( "File could not be uploaded");
    //   }
    // });
  }

}
