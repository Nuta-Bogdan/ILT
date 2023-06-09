import { ActivatedRoute, Params } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { Student } from '../model/Student';
import { ViewTeamService } from '../services/view-team.service';
import {Team} from "../model/Team";
import { ChartType, ChartOptions } from 'chart.js';
import {Grade} from "../model/Grade";
import {ViewGradesTeamService} from "../services/view-grades-team.service";
import { AuthService } from '../services/auth.service';
import { Subject } from 'rxjs';

import {Router} from "@angular/router";

@Component({
  selector: 'app-menu-leader',
  templateUrl: './menu-leader.component.html',
  styleUrls: ['./menu-leader.component.css']
})
export class MenuLeaderComponent implements OnInit {
  refreshDataSubject: Subject<void> = new Subject<void>();
  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private viewTeamService: ViewTeamService,
    private viewGradesTeamService: ViewGradesTeamService,
    private authService: AuthService

  ) {
    this.selectedLeader=this.authService.getLoggedInUser();
  }


  allStudents: Student[]= [] ;
  showAddComponent: boolean = false;
  showDeleteComponent: boolean = false;

  grades:Grade[]=[];
  selectedStudent: Student | null = null;
  selectedStudentSubject: Subject<Student | null> = new Subject<Student | null>();

  showStudentData(student: Student) {
    this.selectedStudent = student;
    this.showAddComponent = false;
    this.showDeleteComponent = false;
    this.selectedStudentSubject.next(this.selectedStudent);
  }



  selectedLeader:Student;

  ngOnInit() {

    this.ViewTeam(this.selectedLeader);
    this.viewGrades();
    this.authService.setLoggedInUser(this.selectedLeader);
  }
  ViewTeam(Leader:Student)
  {
    this.http.get<any>('http://localhost:8080/api/team/' +Leader.email)
      .pipe(map((res) => {
        const Team = [];
        for(const key in res){
          if (key !== 'id') {
            const student = { ...res[key], id: key };
            delete student.id; // Exclude the 'id' property
            Team.push(student);
          }
        }
        return Team;
      }))
      .subscribe((Team) =>{
        console.log(Team[1]);
        const studentObject=Team[1];
        this.allStudents=  Object.values(studentObject);
      })

  }
  private viewGrades() {
    this.viewGradesTeamService.ViewGrades(this.selectedLeader)
      .pipe(
        map((res: Grade[]) => {
          return res.filter((grade: Grade) => grade && Object.keys(grade).length > 1);

        })
      )
      .subscribe((filteredGrades: Grade[]) => {
        this.grades = filteredGrades;

        console.log(filteredGrades);
      });
  }
  addMember() {
    this.showAddComponent = true;
    this.showDeleteComponent = false;
  }

  deleteMember() {
    this.showAddComponent = false;
    this.showDeleteComponent = true;
  }
  handleMemberDeleted() {
    this.ViewTeam(this.selectedLeader);

  }




}
