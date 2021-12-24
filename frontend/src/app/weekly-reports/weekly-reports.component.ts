import { Component, OnInit } from '@angular/core';
import { LocalStorageConstants } from '../Services/LocalStorageConstants';

@Component({
  selector: 'app-weekly-reports',
  templateUrl: './weekly-reports.component.html',
  styleUrls: ['./weekly-reports.component.css']
})
export class WeeklyReportsComponent implements OnInit {

  weeklyReports: {

  }

  constructor() { }

  ngOnInit(): void {
    this.weeklyReports = JSON.parse( localStorage.getItem( LocalStorageConstants.weeklyReports));
  }

}
