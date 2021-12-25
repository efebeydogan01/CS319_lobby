import {Component, Input, OnInit} from '@angular/core';
import {SeatComponent} from "../seat/seat.component";
import {Subscription, take} from "rxjs";
import {SeatService} from "../Services/seat.service";
import {InformationService} from "../Services/information.service";
import {LocalStorageConstants} from "../Services/LocalStorageConstants";

@Component({
  selector: 'app-instructor-seating-plan',
  templateUrl: './instructor-seating-plan.component.html',
  styleUrls: ['./instructor-seating-plan.component.css']
})
export class InstructorSeatingPlanComponent implements OnInit {
  @Input() i: number = -1;
  @Input() courseName: string = '';
  @Input() sectionNo: number = -1;
  @Input() seating: {
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
  }[] = [];

  constructor( private informationService:InformationService) { }

  ngOnInit(): void {
    const userData = JSON.parse(localStorage.getItem(LocalStorageConstants.userData));
  }
}
