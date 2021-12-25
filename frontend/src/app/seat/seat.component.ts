import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ClassInfoComponent} from "../class-info/class-info.component";
import {Subject} from "rxjs";
import {SeatService} from "../Services/seat.service";

@Component({
  selector: 'app-seat',
  templateUrl: './seat.component.html',
  styleUrls: ['./seat.component.css']
})
export class SeatComponent implements OnInit {
  seatStatus = {'no': 'bg-secondary', 'empty': 'bg-white', 'occupied': 'bg-primary', 'my': 'bg-success', 'old': 'bg-faded-success'};
  bntStyle: string = this.seatStatus['no'];
  @Input() i: number = -1;
  @Input() exists: boolean = false;
  @Input() ownedSeat: number = -1;
  @Input() seatOwner: {
    name: string,
    bilkentId: number
  } = null;
  @Output("updateSeat") updateSeat: EventEmitter<number> = new EventEmitter<number>();
  @Output( "seatEmitter") seatEmitter: EventEmitter<SeatComponent> = new EventEmitter<SeatComponent>();
  constructor() {
  }

  ngOnInit(): void {
    this.seatEmitter.next( this);
    if (this.exists) {
      if (this.ownedSeat == this.i)
        this.bntStyle = this.seatStatus['my'];
      else if (this.seatOwner)
        this.bntStyle = this.seatStatus['occupied'];
      else
        this.bntStyle = this.seatStatus['empty'];
    }
  }

  selectSeat() {
    if (this.exists && this.bntStyle === this.seatStatus['empty']) {
      this.bntStyle = this.seatStatus['my'];
      this.updateSeat.emit(this.i);
      console.log(this.i);
    }
  }

  unselectSeat() {
    console.log("old seat")
    if (this.exists) {
      if (this.ownedSeat == this.i)
        this.bntStyle = this.seatStatus['old'];
      else
        this.bntStyle = this.seatStatus['empty'];
    }
  }
}
