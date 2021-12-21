import {Component, OnDestroy, OnInit} from '@angular/core';
import {SeatComponent} from "../seat/seat.component";
import {SeatService} from "../Services/seat.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-class-info',
  templateUrl: './class-info.component.html',
  styleUrls: ['./class-info.component.css']
})
export class ClassInfoComponent implements OnInit, OnDestroy {
  seats: SeatComponent[] = new Array();
  seatSubscriber: Subscription = new Subscription;
  selectedSeat: number = -1;
  constructor( private seatService: SeatService) { }

  ngOnInit(): void {
    this.seatSubscriber = this.seatService.seatSub.subscribe(
      seat => {
        this.seats.push( seat);
        console.log( seat.i);
      }
    );
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
}
