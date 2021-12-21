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
  bntStyle: string;
  @Input() i: number;
  @Output("updateSeat") updateSeat: EventEmitter<number> = new EventEmitter<number>();
  constructor( private seatService: SeatService) {
    this.bntStyle = '';
    this.i = -1;
  }

  ngOnInit(): void {
    this.seatService.emitSeat(this);
  }

  selectSeat() {
    if (this.bntStyle === '') {
      this.bntStyle = 'bg-success';
      this.updateSeat.emit( this.i);
      console.log(this.i);
    }
  }

  unselectSeat() {
    this.bntStyle = '';
  }
}
