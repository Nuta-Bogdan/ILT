import { Component, OnInit,Output,EventEmitter } from '@angular/core';
import {STUDENTS} from "../student_lists";
import {Student} from "../model/Student";
import {MatInputModule} from '@angular/material/input';
import {NgFor} from '@angular/common';
import {MatSelectModule} from '@angular/material/select';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormControl, FormsModule} from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import {NgForm} from "@angular/forms";
import { ViewTeamService } from '../services/view-team.service';
import {Team} from "../model/Team";
import { ChartType, ChartOptions } from 'chart.js';
import {Grade} from "../model/Grade";
import {ViewGradesTeamService} from "../services/view-grades-team.service";
import { AuthService } from '../services/auth.service';
import {map, Subject} from 'rxjs';
import {Router} from "@angular/router";



@Component({
  selector: 'app-assign-tasks',
  templateUrl: './assign-tasks.component.html',
  styleUrls: ['./assign-tasks.component.css']
})
export class AssignTasksComponent {
  selectedValue = "";
  selectedCar = "";
  selectedMentor: Student;
  allLeaders: Student[] = [];
  allStudents:Student[]=[];

selectedLeader!:Student;

  selectedStudents = new FormControl([]);



  constructor(private http: HttpClient, private authService: AuthService, private router: Router, private ViewTeamService: ViewTeamService)
  {
    this.selectedMentor = this.authService.getLoggedInUser();
  }



  ngOnInit() {
    this.viewTeamLeaders();
  }

  private viewTeamLeaders() {
    this.ViewTeamService.ViewTeamLeaders().subscribe((leaders) => {
      console.log(leaders);
      this.allLeaders = leaders;
    });
  }

  onSelectLeader(leader: Student) {
    this.selectedLeader = leader;

    this.http
      .get<any>('http://localhost:8080/api/team/' + leader.email)
      .pipe(
        map((res) => {
          const team = [];
          for (const key in res) {
            if (key !== 'id') {
              const student = { ...res[key], id: key };
              delete student.id; // Exclude the 'id' property
              team.push(student);
            }
          }
          return team;
        })
      )
      .subscribe((team) => {
        console.log(team[1]);
        const studentObject = team[1];
        this.allStudents = Object.values(studentObject);
      });
  }
  addTask(form:NgForm)
  {
    if (form.valid) {

      const taskName = form.controls['taskName'].value;
      const leader = form.controls['leader'].value;
      const students = form.value.students;

      // Extract the emails from the selected students
      console.log(taskName);
      console.log(leader);
      console.log(students);
    }

  }
}
