import { Component, OnInit,Output,EventEmitter } from '@angular/core';
import { ActivityService } from "../services/activity.service";
import { PROJECTS } from "../project_list";
import {NgForm} from "@angular/forms";

import {ActivatedRoute, Params, Router} from '@angular/router';
import { HttpClient } from '@angular/common/http';

import { map } from 'rxjs/operators';
import { Student } from '../model/Student';
import { ViewTeamService } from '../services/view-team.service';
import {Team} from "../model/Team";
import { ChartType, ChartOptions } from 'chart.js';
import {Grade} from "../model/Grade";
import {ViewGradesTeamService} from "../services/view-grades-team.service";
import { AuthService } from '../services/auth.service';
import { Subject } from 'rxjs';
@Component({
  selector: 'app-create-project',
  templateUrl: './create-project.component.html',
  styleUrls: ['./create-project.component.css']
})
export class CreateProjectComponent implements OnInit {
  projects = PROJECTS;
  addingActivity : boolean = false;
  formData: any={};
  selectedMentor:Student;

  constructor(private activityService: ActivityService, private http: HttpClient,private authService: AuthService,private router: Router) {
    this.selectedMentor=this.authService.getLoggedInUser();
  }

  ngOnInit() {
    this.getProjects();
  }

  private getProjects() {
    this.activityService.getProjects().subscribe((res) => {
      console.log(res);
      this.projects = res;
    });
  }

  addProject() {
    this.addingActivity= true;
  }
  submitForm(){
    const ActivityName = this.formData.name;
    const Tasks = [];
    this.http.post('http://localhost:8080/api/activity', {name: ActivityName, tasks: []}).subscribe((res)=>
    {
      console.log(res);
      this.reload();
    })
  }

  reload()
  {
    this.authService.setLoggedInUser(this.selectedMentor);
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate(['/mentor']);
    });
  }

}
