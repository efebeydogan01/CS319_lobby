import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ClassInfoComponent} from "../class-info/class-info.component";
import {Subject} from "rxjs";
import {SeatService} from "../Services/seat.service";
import {LocalStorageConstants} from "../Services/LocalStorageConstants";

@Component({
  selector: 'app-seat',
  templateUrl: './seat.component.html',
  styleUrls: ['./seat.component.css']
})
export class SeatComponent implements OnInit {
  seatStatus = {'no': 'bg-secondary', 'empty': 'btn-white', 'occupied': 'bg-primary', 'my': 'bg-success', 'new': 'bg-warning'};
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
  userRole: string = JSON.parse( localStorage.getItem( LocalStorageConstants.userData)).role;
  tooltip: string = '';

  constructor() {
  }

  ngOnInit(): void {
    if (this.userRole === 'STUDENT')
      this.seatEmitter.next( this);

    if (this.exists) {
      if (this.ownedSeat == this.i)
        this.bntStyle = this.seatStatus['my'];
      else if (this.seatOwner)
        this.bntStyle = this.seatStatus['occupied'];
      else
        this.bntStyle = this.seatStatus['empty'];
    }
    if (this.seatOwner) { if (this.userRole === 'ACADEMIC_PERSONNEL') this.tooltip = this.seatOwner.name + ' ' + this.seatOwner.bilkentId; else if (this.userRole === 'STUDENT' && (this.i - this.ownedSeat == 1 && this.i % 10 != 0 || this.i - this.ownedSeat == -1 && this.i % 10 != 9)) this.tooltip = this.seatOwner.name; }
  }

  selectSeat() {
    if (this.userRole === 'STUDENT' && this.exists && this.ownedSeat != this.i && this.bntStyle === this.seatStatus['empty']) {
      this.bntStyle = this.seatStatus['new'];
      this.updateSeat.emit(this.i);
      console.log("new selection: " + this.i);
    }
  }

  unselectSeat() {
    if (this.userRole === 'STUDENT' && this.exists && this.ownedSeat != this.i)
        this.bntStyle = this.seatStatus['empty'];
  }
}
