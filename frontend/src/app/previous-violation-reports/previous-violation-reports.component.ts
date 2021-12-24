import { Component, OnInit } from '@angular/core';
import { InformationService } from '../Services/information.service';
import { LocalStorageConstants } from '../Services/LocalStorageConstants';

@Component({
  selector: 'app-previous-violation-reports',
  templateUrl: './previous-violation-reports.component.html',
  styleUrls: ['./previous-violation-reports.component.css']
})
export class PreviousViolationReportsComponent implements OnInit {

  previousReports: {
      id: number,
      message: string,
      place: string
  }[] = null;

  constructor(private informationService: InformationService) { }

  ngOnInit(): void {
    this.informationService.getPreviousViolationReports().subscribe( () => {
      this.previousReports = JSON.parse( localStorage.getItem( LocalStorageConstants.violationReports));
    });
  }
}
