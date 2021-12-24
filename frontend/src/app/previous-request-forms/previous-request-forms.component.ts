import { Component, OnInit } from '@angular/core';
import { InformationService } from '../Services/information.service';
import { LocalStorageConstants } from '../Services/LocalStorageConstants';

@Component({
  selector: 'app-previous-request-forms',
  templateUrl: './previous-request-forms.component.html',
  styleUrls: ['./previous-request-forms.component.css']
})
export class PreviousRequestFormsComponent implements OnInit {

  previousForms: {
    form: {
      user: {
        id: string
      },
      title: string,
      request: string
    } []
  } = null;

  constructor( private informationService: InformationService) { }

  ngOnInit(): void {
    this.informationService.getPreviousRequestForms().subscribe( () => {
      this.previousForms = JSON.parse( localStorage.getItem( LocalStorageConstants.requestForms));
    });
  }

}
