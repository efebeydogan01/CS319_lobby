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

const appRoutes: Routes =[
  { path: '', component: LoginComponent},
  { path: 'personal-info', component: PersonalInfoComponent},
  { path: 'class-info', component: ClassInfoComponent},
  { path: 'general-info', component: GeneralInfoComponent},
  { path: 'social', component: SocialComponent},
  { path: 'report-request', component: ReportRequestComponent}
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
    ReportRequestComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(appRoutes),
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
