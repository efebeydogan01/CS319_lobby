import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {RouterModule, Routes} from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { PersonalInfoComponent } from './personal-info/personal-info.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { TopbarComponent } from './topbar/topbar.component';
import { FormsModule } from "@angular/forms";
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

const appRoutes: Routes =[
  { path: '', component: LoginComponent},
  { path: 'personal-info', component: PersonalInfoComponent, canActivate: [AuthGuard]},
  { path: 'search-user-info', component: SearchUserInfoComponent, canActivate: [AuthGuard]}, // admin & health center employee
  { path: 'class-info', component: ClassInfoComponent, canActivate: [AuthGuard]},
  { path: 'general-info', component: GeneralInfoComponent, canActivate: [AuthGuard]},
  { path: 'general-info/guidelines', component: GuidelinesComponent, canActivate: [AuthGuard]},
  { path: 'general-info/weekly-reports', component: WeeklyReportsComponent, canActivate: [AuthGuard]},
  { path: 'social', component: SocialComponent, canActivate: [AuthGuard]},
  { path: 'report-request', component: ReportRequestComponent, canActivate: [AuthGuard]}
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
    SeatComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(appRoutes),
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
