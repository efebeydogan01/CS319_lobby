import {Component, OnInit} from '@angular/core';
import {LoginService} from "../Services/login-service.service";
import {Subscription, take} from "rxjs";
import {InformationService} from "../Services/information.service";

@Component({
  selector: 'app-personal-info',
  templateUrl: './personal-info.component.html',
  styleUrls: ['./personal-info.component.css']
})
export class PersonalInfoComponent implements OnInit {
  // loginSubs: Subscription = new Subscription();
  covidStatus: string = "";
  constructor( private loginService: LoginService, private informationService: InformationService) { }

  userData: {
    id: number,
    name: string,
    dob: string,
    phoneNumber: string,
    age: number,
    uuid: string,
    role: string
  } = null;

  specificData = null;
  ngOnInit(): void {
    const localUser = localStorage.getItem('userData');
    if ( localUser) {
      this.userData = JSON.parse(localUser);
      this.userData.dob = this.userData.dob.substring(0, 10);
      this.informationService.getCovidInfo( this.userData.uuid).pipe( take(1)).subscribe( {
        next: (data) => {
          this.covidStatus = data.data.status;
        }
      });

      this.informationService.getRoleInfo( this.userData.uuid, this.userData.role).pipe( take(1)).subscribe( {
        next: (data) => {

        }
      });
    }
  }


}
