import { Component, OnInit } from '@angular/core';
import { InformationService } from '../Services/information.service';
import { LocalStorageConstants } from '../Services/LocalStorageConstants';

@Component({
  selector: 'app-previous-feedback-forms',
  templateUrl: './previous-feedback-forms.component.html',
  styleUrls: ['./previous-feedback-forms.component.css']
})
export class PreviousFeedbackFormsComponent implements OnInit {

  previousForms: {
    form: {
      user: {
        id: string
      },
      rating: number,
      title: string,
      feedback: string
    } []
  } = null;

  constructor(private informationService: InformationService) { }

  ngOnInit(): void {
    this.informationService.getPreviousFeedbackForms().subscribe( () => {
      this.previousForms = JSON.parse( localStorage.getItem( LocalStorageConstants.feedbackForms));
    });
  }

}
