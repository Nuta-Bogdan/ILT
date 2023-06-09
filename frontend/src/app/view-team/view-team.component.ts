import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Component, OnInit} from '@angular/core';
import {map, pipe} from "rxjs";
import {Student} from "../model/Student";
import {Team} from "../model/Team";
import {ViewTeamService} from "../services/view-team.service";
import { AuthService } from '../services/auth.service';
import { NgForm } from "@angular/forms";
import {Router} from "@angular/router";


@Component({
  selector: 'app-view-team',
  templateUrl: './view-team.component.html',
  styleUrls: ['./view-team.component.css']
})
export class ViewTeamComponent implements OnInit {
  allStudents: Student[]= [] ;
  selectedStud!: Student;
  selected!:boolean;
  opt!:number;
  selectedGrading!: boolean;
  selectedAttendance!: boolean;
  selectedLeader?: Student;
  allLeaders: Student[] = [];
  options: string[] = [];
  formData: any;
  selectedMentor:Student;

  constructor(private http: HttpClient, private ViewTeamService: ViewTeamService,private authService: AuthService,private router: Router) {
    this.selectedMentor=this.authService.getLoggedInUser();
    console.log(this.selectedMentor);

  }

  ngOnInit() {
    this.ViewTeamLeaders();
    this.authService.setLoggedInUser(this.selectedMentor);
    console.log(this.selectedMentor);
    this.opt=0;


  }

  private ViewTeamLeaders(){
    this.ViewTeamService.ViewTeamLeaders().subscribe((Leaders) => {
      console.log(Leaders);
      this.allLeaders = Leaders;
    });
}

  onSelectLeader(Leader: Student){
    this.selectedLeader=Leader;
    this.http.get<any>('http://localhost:8080/api/team/' + Leader.email)
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
  selectedStudent(student:Student){
    this.selectedStud = student;
    this.selected=true;

  }
  onGrading(){
    this.selectedGrading=true;
  }
  onAttendance(){
    this.selectedAttendance=true;
  }

  SubmitForm(form: NgForm){
    const TaskName = form.value.task;
    console.log(form.value.task);
    const Grade = form.value.grade;
    const Comment = form.value.comment;
    this.http.post('http://localhost:8080/api/grade', {task: TaskName, grade: Grade, comment: Comment, mentor: this.selectedMentor.email, email: this.selectedStud.email})
      .subscribe((res)=>{
      console.log(res);
      this.reload();
    });
  }

  reload()
  {
    this.authService.setLoggedInUser(this.selectedMentor);
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate(['/mentor']);
    });
  }
  SubmitForm1(form: NgForm){


    const Task=form.value.taskName;
    const Att=form.value.att;
    if(Att==="present")
      this.opt=1;
    this.http.patch('http://localhost:8080/api/attendance/'+this.selectedStud.email+'/'+Task+'/'+this.opt,{})
      .subscribe((res)=>{
        console.log(res);
        this.reload();
      });




  }


}
