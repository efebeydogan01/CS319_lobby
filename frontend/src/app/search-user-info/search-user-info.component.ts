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
  term: any = "";
  userRole: string = JSON.parse( localStorage.getItem( LocalStorageConstants.userData)).role;

  filteredResults: number = 0;
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
      this.filteredResults = this.covidInformations.length;
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
      "result": testResult.toUpperCase()
    };

    console.log(testDate.substring(0,10));

    this.informationService.addTestResult(test).subscribe( () => {
      form.reset();
      window.location.reload();
    });
  }

  filter() {
    console.log( "inside filter");
    if ( this.term === "" || this.covidInformations?.length === 0) {
      this.filteredResults = this.covidInformations?.length;
      return this.covidInformations;
    }

    const filtered = this.covidInformations.filter( info =>
      info.user.name.toLowerCase().indexOf( this.term.toLowerCase()) !== -1 ||
      (String)(info.user.bilkentId).toLowerCase().indexOf( this.term.toLowerCase()) !== -1 ||
      info.user.role.toLowerCase().indexOf( this.term.toLowerCase()) !== -1 ||
      info.status.toLowerCase().indexOf( this.term.toLowerCase()) !== -1);
    this.filteredResults = filtered.length;
    return filtered;
  }
}
