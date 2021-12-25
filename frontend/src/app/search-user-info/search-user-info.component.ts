import { Component, OnInit } from '@angular/core';
import { InformationService } from '../Services/information.service';
import {LocalStorageConstants} from "../Services/LocalStorageConstants";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-search-user-info',
  templateUrl: './search-user-info.component.html',
  styleUrls: ['./search-user-info.component.css']
})
export class SearchUserInfoComponent implements OnInit {
  userRole: string = JSON.parse( localStorage.getItem( LocalStorageConstants.userData)).role;

  date = new Date();

  covidInformations: {
    allowedOnCampus: boolean,
    hesCode: string,
    id: string,
    status: string,
    user: {
      age: number,
      bilkentId: number,
      dateOfBirth: string,
      id: number,
      name: string,
      password: string,
      phoneNumber: string,
      role: string
    }
  } [] = null;

  constructor(private informationService: InformationService) { }

  ngOnInit(): void {
    this.informationService.getAllCovidInfo().subscribe( () => {
      this.covidInformations = JSON.parse( localStorage.getItem( LocalStorageConstants.allCovidInfo));
    });
  }

  addTestResult(user: {
    age: number,
    bilkentId: number,
    dateOfBirth: string,
    id: number,
    name: string,
    password: string,
    phoneNumber: string,
    role: string}, testResult: string, testType: string, testDate:string, form: NgForm) {
    let test = {
      "user": user,
      "testDate": testDate.substring(0,10),
      "type": testType,
      "result": testResult
    };

    console.log(testDate.substring(0,10));

    this.informationService.addTestResult(test).subscribe( () => {
      form.reset();
    });
  }
}
