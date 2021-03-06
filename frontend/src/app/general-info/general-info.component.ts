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
// This component shows all the information about the situation of the pandemic in the university
export class GeneralInfoComponent implements OnInit {

  userRole:string = "";

  // The cases are categorized as academic, admin, staff, student and vaccination rates
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
      const uuid: string = JSON.parse( localStorage.getItem( LocalStorageConstants.userData)).uuid;
      this.informationService.getGeneralInfo().subscribe( () => {
        const userData = {
          id: uuid
        };
        const notification = {
          user: userData,
          receivers: "ALL",
          title: announcement.title,
          message: announcement.announcementText
        }
        this.informationService.notificationRequest(notification).subscribe( {
          next: () => {
            this.informationService.getNotifications( userData.id).subscribe( {
              next: () => {
                this.generalInfo = JSON.parse( localStorage.getItem( LocalStorageConstants.generalInfo));
                // page is reloaded after data is received so the user can see updated info
                window.location.reload();
              },
            });
          },
          error: () => {
          }
        });
      });
    });
  }
}

