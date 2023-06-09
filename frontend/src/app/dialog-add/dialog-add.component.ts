import {ActivatedRoute, Params, Router} from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit,Output,EventEmitter } from '@angular/core';
import { map } from 'rxjs/operators';
import { Student } from '../model/Student';
import { ViewTeamService } from '../services/view-team.service';
import {Team} from "../model/Team";
import { ChartType, ChartOptions } from 'chart.js';
import {Grade} from "../model/Grade";
import {ViewGradesTeamService} from "../services/view-grades-team.service";
import { AuthService } from '../services/auth.service';
import { Subject } from 'rxjs';

import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-dialog-add',
  templateUrl: './dialog-add.component.html',
  styleUrls: ['./dialog-add.component.css']
})
export class DialogAddComponent implements OnInit{

  @Output() memberAdded: EventEmitter<string> = new EventEmitter<string>();
  selectedLeader:Student;
  addStudent(form: NgForm)
  {
    if (form.valid) {
      const name = form.value.username;
      const password1 = form.value.password;
      const email=form.value.email;
      this.http.post<any>('http://localhost:8080/api/student/'+this.selectedLeader.email+'/'+password1, { name:name,email:email,leader:false })
        .subscribe((response) => {
          console.log(response);

        });

    }

  }

  constructor(private http: HttpClient, private authService: AuthService,private router: Router) {
    this.selectedLeader=this.authService.getLoggedInUser();


  }


  ngOnInit() {
  }

  reload()
  {
    this.authService.setLoggedInUser(this.selectedLeader);
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate(['/leader']);
    });
  }
}
