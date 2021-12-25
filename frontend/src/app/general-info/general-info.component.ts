import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { LocalStorageConstants } from '../Services/LocalStorageConstants';
import { Router, RouterLinkActive } from '@angular/router';
import { InformationService } from '../Services/information.service';
import { take } from 'rxjs';

@Component({
  selector: 'app-general-info',
  templateUrl: './general-info.component.html',
  styleUrls: ['./general-info.component.css']
})
export class GeneralInfoComponent implements OnInit {


  userRole:string = "";

  generalInfo: {
    announcements: {
      id: string,
      title: string,
      date: string,
      announcementText: string
    }[],
    academicCases: number,
    adminCases: number,
    staffCases: number,
    studentCases: number,
    vaccinationRate1: number,
    vaccinationRate2: number,
  } = null;

  constructor( private informationService: InformationService, private router: Router) { }

  ngOnInit(): void {
    this.generalInfo = JSON.parse( localStorage.getItem( LocalStorageConstants.generalInfo));
    this.userRole = JSON.parse( localStorage.getItem( LocalStorageConstants.userData)).role;
    this.informationService.getGeneralInfo().subscribe( () => {
      this.generalInfo = JSON.parse( localStorage.getItem( LocalStorageConstants.generalInfo));
    });
  }

  getGuidelines() {
    this.informationService.getGuidelines().subscribe( () => {
      this.router.navigate(["/guidelines"]);
    });
  }

  getWeeklyReports() {
    this.informationService.getGuidelines().subscribe( () => {
      this.router.navigate(["/weeklyReports"]);
    });
  }

  makeAnnouncement(title: string, text: string) {
    let announcement = {
      "title": title,
      "date": (new Date()).toDateString(),
      "announcementText" : text
    };

    this.informationService.makeAnnouncement(announcement).subscribe( () => {
      this.informationService.getGeneralInfo().subscribe( () => {
        this.generalInfo = JSON.parse( localStorage.getItem( LocalStorageConstants.generalInfo));
        window.location.reload();
      });
    });
  }
}
