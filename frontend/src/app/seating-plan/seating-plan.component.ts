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
  seats: SeatComponent[] = [];
  seatSubscriber: Subscription = new Subscription;
  ownedSeat: number = -1;
  selectedSeat: number = -1;

  constructor( private seatService: SeatService, private informationService:InformationService) { }

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

  addSeatComponent(seat: SeatComponent) {
    this.seats[seat.i] = seat;
  }

  ngOnDestroy() {
    //this.seatSubscriber.unsubscribe();
  }

  updateSelectedSeat(seatNo: number) {
    if (this.selectedSeat != -1)
      this.seats[this.selectedSeat].unselectSeat();

    this.selectedSeat = seatNo;
    console.log("owned seat: " + this.ownedSeat)
  }

  resetSelection(): void {
    if (this.selectedSeat != -1 && this.selectedSeat != this.ownedSeat)
      this.seats[this.selectedSeat].unselectSeat();
    this.selectedSeat = -1;
  }

  saveSelection(): void {
    const userData = JSON.parse(localStorage.getItem(LocalStorageConstants.userData));

    if (this.selectedSeat != -1) {
      let seat = {
        courseName: this.courseName,
        sectionNo: this.sectionNo,
        rowNo: Math.floor(this.selectedSeat / 10),
        columnNo: Math.floor(this.selectedSeat % 10)
      }

      console.log(seat)
      this.informationService.makeSeatSelection(userData.uuid, seat).pipe(take(1)).subscribe({
        next: () => {
          this.ownedSeat = this.selectedSeat;
          this.resetSelection();
          window.location.reload();
        },
        error: () => {
          console.log("Seat not available")
        }
      });
    }
  }
}
