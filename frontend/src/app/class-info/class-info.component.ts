import {Component, OnDestroy, OnInit} from '@angular/core';
import {SeatComponent} from "../seat/seat.component";
import {SeatService} from "../Services/seat.service";
import {Subscription, take} from "rxjs";
import {InformationService} from "../Services/information.service";
import {LocalStorageConstants} from "../Services/LocalStorageConstants";

@Component({
  selector: 'app-class-info',
  templateUrl: './class-info.component.html',
  styleUrls: ['./class-info.component.css']
})
// This component holds all the information related to the sections of a user, such as their neighbors, sections etc.
export class ClassInfoComponent implements OnInit {
  sectionsWithSeats: {
    section: {
      id: string,
      academicPersonnel: {
        id: string,
        department: string,
        user: {
          name: string,
        }
      },
      courseName: string,
      sectionNo: number,
      classroom: string
    },
    seats: {
      id: string,
      exists: boolean,
      row: number,
      column: number,
      student: {
        user: {
          name: string,
          bilkentId: number
        }
      }
    }[]
  }[] = null;

  constructor(private informationService:InformationService) { }

  ngOnInit(): void {
    const userData = JSON.parse(localStorage.getItem(LocalStorageConstants.userData));

    this.informationService.getSectionsWithSeats(userData.uuid).pipe( take( 1)).subscribe( {
      next: () => {
        this.sectionsWithSeats = JSON.parse( localStorage.getItem( LocalStorageConstants.sections));
      }
    });
  }
}
