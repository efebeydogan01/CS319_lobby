import { Component, OnInit } from '@angular/core';
import { LocalStorageConstants } from '../Services/LocalStorageConstants';

@Component({
  selector: 'app-guidelines',
  templateUrl: './guidelines.component.html',
  styleUrls: ['./guidelines.component.css']
})
export class GuidelinesComponent implements OnInit {

  guidelines: {

  }



  constructor() { }

  ngOnInit(): void {
    this.guidelines = JSON.parse( localStorage.getItem( LocalStorageConstants.guidelines));
  }

}
