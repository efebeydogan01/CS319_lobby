import {Component, OnDestroy, OnInit} from '@angular/core';
import {SeatComponent} from "../seat/seat.component";
import {Subscription, take} from "rxjs";
import {SeatService} from "../Services/seat.service";
import {LocalStorageConstants} from "../Services/LocalStorageConstants";
import {InformationService} from "../Services/information.service";

@Component({
  selector: 'app-seating-plan',
  templateUrl: './seating-plan.component.html',
  styleUrls: ['./seating-plan.component.css']
})
export class SeatingPlanComponent implements OnInit, OnDestroy {
  seats: SeatComponent[] = new Array();
  seatSubscriber: Subscription = new Subscription;
  selectedSeat: number = -1;

  seating: {
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
  }[] = null;

  constructor( private seatService: SeatService, private informationService:InformationService) { }

  ngOnInit(): void {
    console.log("seatingplan")

    this.seatSubscriber = this.seatService.seatSub.subscribe(
      seat => {
        this.seats.push( seat);
        //console.log( seat.i);
      }
    );

    this.getSeatingPlan();
  }

  ngOnDestroy() {
    this.seatSubscriber.unsubscribe();
  }

  updateSelectedSeat(seatNo: number) {
    if (this.selectedSeat != -1)
      this.seats[this.selectedSeat].unselectSeat();

    this.selectedSeat = seatNo;
    console.log(this.selectedSeat);
  }

  getSeatingPlan(): void {
    let section = {
      "courseName": "CS319",
      "sectionNo": 1
    };

    this.informationService.getSeatingPlan(section).pipe( take( 1)).subscribe( {
      next: () => {
      }
    });

    const seatingPlan = JSON.parse( localStorage.getItem( LocalStorageConstants.seating));
    if (seatingPlan) {
      this.seating = seatingPlan;
    }
  }
}
