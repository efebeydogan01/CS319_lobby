import { Component, OnInit } from '@angular/core';
import {LocalStorageConstants} from "../Services/LocalStorageConstants";

@Component({
  selector: 'app-search-user-info',
  templateUrl: './search-user-info.component.html',
  styleUrls: ['./search-user-info.component.css']
})
export class SearchUserInfoComponent implements OnInit {
  userRole: string = JSON.parse( localStorage.getItem( LocalStorageConstants.userData)).role;

  constructor() { }

  ngOnInit(): void {
  }

}
