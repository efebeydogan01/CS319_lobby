import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {RouterModule, Routes} from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { PersonalInfoComponent } from './personal-info/personal-info.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { TopbarComponent } from './topbar/topbar.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { GeneralInfoComponent } from './general-info/general-info.component';
import { ClassInfoComponent } from './class-info/class-info.component';
import { SocialComponent } from './social/social.component';
import { ReportRequestComponent } from './report-request/report-request.component';
import { GuidelinesComponent } from './guidelines/guidelines.component';
import { WeeklyReportsComponent } from './weekly-reports/weekly-reports.component';
import { SearchUserInfoComponent } from './search-user-info/search-user-info.component';
import { SearchUserBarComponent } from './search-user-bar/search-user-bar.component';
import {HttpClientModule} from "@angular/common/http";
import {LoadingSpinnerComponent} from "./loading-spinner/loading-spinner.component";
import { SeatComponent } from './seat/seat.component';
import {AuthGuard} from "./Services/auth.guard";
import { SendNotificationPopupComponent } from './send-notification-popup/send-notification-popup.component';
import {LocalStorageConstants} from "./Services/LocalStorageConstants";
import { SeatingPlanComponent } from './seating-plan/seating-plan.component';
import { PreviousViolationReportsComponent } from './previous-violation-reports/previous-violation-reports.component';
import { PreviousRequestFormsComponent } from './previous-request-forms/previous-request-forms.component';
import { PreviousFeedbackFormsComponent } from './previous-feedback-forms/previous-feedback-forms.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { InstructorSeatingPlanComponent } from './instructor-seating-plan/instructor-seating-plan.component';

const appRoutes: Routes =[
  { path: '', component: LoginComponent},
  { path: 'personal-info', component: PersonalInfoComponent, canActivate: [AuthGuard]},
  { path: 'search-user-info', component: SearchUserInfoComponent, canActivate: [AuthGuard]}, // admin & health center employee
  { path: 'class-info', component: ClassInfoComponent, canActivate: [AuthGuard]},
  { path: 'general-info', component: GeneralInfoComponent, canActivate: [AuthGuard]},
  { path: 'general-info/guidelines', component: GuidelinesComponent, canActivate: [AuthGuard]},
  { path: 'general-info/weekly-reports', component: WeeklyReportsComponent, canActivate: [AuthGuard]},
  { path: 'social', component: SocialComponent, canActivate: [AuthGuard]},
  { path: 'report-request', component: ReportRequestComponent, canActivate: [AuthGuard]},
  { path: 'report-request/previous-violation-reports', component: PreviousViolationReportsComponent, canActivate: [AuthGuard]},
  { path: 'report-request/previous-request-forms', component: PreviousRequestFormsComponent, canActivate: [AuthGuard]},
  { path: 'report-request/previous-feedback-forms', component: PreviousFeedbackFormsComponent, canActivate: [AuthGuard]},
  { path: "**",redirectTo: localStorage.getItem( LocalStorageConstants.userData) ? 'personal-info' : ''}
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PersonalInfoComponent,
    SidebarComponent,
    TopbarComponent,
    GeneralInfoComponent,
    ClassInfoComponent,
    SocialComponent,
    ReportRequestComponent,
    GuidelinesComponent,
    WeeklyReportsComponent,
    SearchUserInfoComponent,
    SearchUserBarComponent,
    LoadingSpinnerComponent,
    SeatComponent,
    SendNotificationPopupComponent,
    SeatingPlanComponent,
    PreviousViolationReportsComponent,
    PreviousRequestFormsComponent,
    PreviousFeedbackFormsComponent,
    InstructorSeatingPlanComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        RouterModule.forRoot(appRoutes),
        FormsModule,
        HttpClientModule,
        ReactiveFormsModule,
        NgbModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
