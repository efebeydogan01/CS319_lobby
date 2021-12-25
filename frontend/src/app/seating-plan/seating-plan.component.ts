import {AfterViewInit, Component, Input, OnDestroy, OnInit} from '@angular/core';
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
  @Input() i: number = -1;
  @Input() sectionName: string = '';
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
  seats: SeatComponent[] = [];
  seatSubscriber: Subscription = new Subscription;
  ownedSeat: number = -1;
  selectedSeat: number = -1;

  constructor( private seatService: SeatService) { }

  ngOnInit(): void {
    const userData = JSON.parse(localStorage.getItem(LocalStorageConstants.userData));
    if (userData?.id && userData.id > -1)
    for (let i = 0; i < this.seating.length; i++) {
      if (this.seating[i]?.student?.user?.bilkentId == userData.id) {
        this.ownedSeat = i;
        break;
      }
    }
  }

  // ngAfterViewInit() {
  //   console.log("selected" + this.selectedSeat)
  //   let section = {
  //     "courseName": "CS319",
  //     "sectionNo": (this.i == 1) ? 4 : 1
  //   };
  //
  //
  //
  //   console.log("get seating length: " + this.seats.length)
  // }

  addSeatComponent(seat: SeatComponent) {
    this.seats[seat.i] = seat;
  }

  ngOnDestroy() {
    //this.seatSubscriber.unsubscribe();
  }

  updateSelectedSeat(seatNo: number) {
    console.log("update selected seat " + this.seats.length)
    if (this.selectedSeat != -1)
      this.seats[this.selectedSeat].unselectSeat();

    this.selectedSeat = seatNo;
    console.log(this.selectedSeat);

    console.log("owned seat: " + this.ownedSeat)
  }

  resetSelection(): void {
    if (this.selectedSeat != -1 && this.selectedSeat != this.ownedSeat)
      this.seats[this.selectedSeat].unselectSeat();
    this.selectedSeat = -1;
  }
}
