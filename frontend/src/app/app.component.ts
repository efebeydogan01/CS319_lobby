import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {LoginService} from "./Services/login-service.service";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'frontend';
  constructor(public router: Router, private loginService: LoginService, private titleService: Title){

  }

  ngOnInit() {
    this.titleService.setTitle("Pandemica");
    this.loginService.autoLogin();
  }
}
