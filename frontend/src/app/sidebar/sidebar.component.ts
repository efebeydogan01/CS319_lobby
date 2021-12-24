import { Component, OnInit } from '@angular/core';
import { Router, RouterLinkActive } from '@angular/router';
import { take } from 'rxjs';
import { InformationService } from '../Services/information.service';
import {LocalStorageConstants} from "../Services/LocalStorageConstants";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css'],
  host: {'class': 'sidenav navbar navbar-vertical navbar-expand-xs fixed-start'}
})
export class SidebarComponent implements OnInit {
  userRole: string = "";

  constructor( private informationService: InformationService, private router: Router) { }

  ngOnInit(): void
  {
    this.userRole = JSON.parse( localStorage.getItem( LocalStorageConstants.userData)).role;
  }

  getGeneralInfo() {
      this.informationService.getGeneralInfo().subscribe( () => {
        this.router.navigate(["/general-info"]);
      });
      //this.routerLink.routerLinkActive = "active";
  }
}
