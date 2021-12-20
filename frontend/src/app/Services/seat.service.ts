import { Injectable } from '@angular/core';
import {Subject} from "rxjs";
import {SeatComponent} from "../seat/seat.component";

@Injectable({
  providedIn: 'root'
})
export class SeatService {
  seatSub: Subject<SeatComponent> = new Subject();
  constructor() { }

  emitSeat( seat: SeatComponent) {
    this.seatSub.next( seat);
  }

  updateSelectedSeat() {

  }
}
