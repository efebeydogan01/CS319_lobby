import {Component, OnDestroy, OnInit} from '@angular/core';
import {SeatComponent} from "../seat/seat.component";
import {SeatService} from "../Services/seat.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-class-info',
  templateUrl: './class-info.component.html',
  styleUrls: ['./class-info.component.css']
})
export class ClassInfoComponent implements OnInit {
  constructor() { }

  ngOnInit(): void {
  }
}
