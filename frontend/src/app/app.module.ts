import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {MatMenuModule} from "@angular/material/menu";
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {AppComponent} from './app.component';
import {HomeComponent} from './home/home.component';
import {RegisterComponent} from './register/register.component';
import {ApplyComponent} from './apply/apply.component';
import {LoginComponent} from './login/login.component';

import {MenuMentorComponent} from './menu-mentor/menu-mentor.component';
import {MatTableModule} from "@angular/material/table";
import {PdfToPngComponent} from './pdf-to-png/pdf-to-png.component';
import {NavigbarComponent} from './navigbar/navigbar.component';
import {SidebarComponent} from './sidebar/sidebar.component';
import {ViewTeamComponent} from './view-team/view-team.component';
import {AssignTasksComponent} from './assign-tasks/assign-tasks.component';
import {CreateProjectComponent} from './create-project/create-project.component';
import {StudentComponent} from './student/student.component';
import {MenuStudentComponent} from './menu-student/menu-student.component';
import {ViewAttendenceComponent} from './view-attendence/view-attendence.component';
import {ViewGradesComponent} from './view-grades/view-grades.component';
import {HttpClientModule} from "@angular/common/http";
import {MenuLeaderComponent} from './menu-leader/menu-leader.component';
import {DialogCreateComponent} from './dialog-create/dialog-create.component';
import {MatSelectModule} from "@angular/material/select";
import {ViewTeamService} from "./services/view-team.service";
import {NgChartsModule} from 'ng2-charts';

import {PieChartComponent} from './pie-chart/pie-chart.component';
import {DialogAddComponent} from './dialog-add/dialog-add.component';
import {DialogDeleteComponent} from './dialog-delete/dialog-delete.component';
import {NewactivityComponent} from './newactivity/newactivity.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RegisterComponent,
    ApplyComponent,
    LoginComponent,
    MenuMentorComponent,
    PdfToPngComponent,
    NavigbarComponent,
    SidebarComponent,
    ViewTeamComponent,
    AssignTasksComponent,
    CreateProjectComponent,
    StudentComponent,
    MenuStudentComponent,
    ViewAttendenceComponent,
    ViewGradesComponent,
    MenuLeaderComponent,
    DialogCreateComponent,
    PieChartComponent,
    DialogAddComponent,
    DialogDeleteComponent,
    NewactivityComponent
  ],

  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    MatMenuModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgChartsModule,


    RouterModule.forRoot([
      {path: '', component: HomeComponent},
      {path: 'login', component: LoginComponent},
      {path: 'apply', component: ApplyComponent},
      {path: 'mentor', component: MenuMentorComponent},
      {path: 'register', component: RegisterComponent},
      {path: 'student', component: MenuStudentComponent},
      {path: 'selfgrade', component: ViewGradesComponent},
      {path: 'selfattendence', component: ViewAttendenceComponent},
      {path: 'leader', component: MenuLeaderComponent},
      {
        path: 'activity',component:CreateProjectComponent
      }// <-- Add this route
    ]),
    MatTableModule,
    MatSelectModule,
  ],
  providers: [ViewTeamService, MenuLeaderComponent],
  bootstrap: [AppComponent]
})
export class AppModule {
}
