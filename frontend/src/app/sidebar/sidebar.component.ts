import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css'],
  host: {'class': 'sidenav navbar navbar-vertical navbar-expand-xs fixed-start'}
})
export class SidebarComponent implements OnInit {
  userRole: string = "";

  constructor() { }

  ngOnInit(): void {
    this.userRole = JSON.parse( localStorage.getItem( 'userData')).role;
  }

}
