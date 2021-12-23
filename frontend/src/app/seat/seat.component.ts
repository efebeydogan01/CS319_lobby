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
  @Output("updateSeat") updateSeat: EventEmitter<number> = new EventEmitter<number>();
  constructor( private seatService: SeatService) {
  }

  ngOnInit(): void {
    this.seatService.emitSeat(this);
    if (this.exists)
      this.bntStyle = this.seatStatus['empty'];
  }

  selectSeat() {
    if (this.exists && this.bntStyle === this.seatStatus['empty']) {
      this.bntStyle = this.seatStatus['my'];
      this.updateSeat.emit( this.i);
      console.log(this.i);
    }
  }

  unselectSeat() {
    if (this.exists)
      this.bntStyle = this.seatStatus['empty'];
  }
}
