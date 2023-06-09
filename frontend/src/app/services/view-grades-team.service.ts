import { Injectable } from '@angular/core';
import { map } from "rxjs/operators";
import { Student } from "../model/Student";
import { HttpClient } from "@angular/common/http";
import {Grade} from "../model/Grade";

@Injectable({
  providedIn: 'root'
})
export class ViewGradesTeamService {

  constructor(private http: HttpClient) {}
  ViewGrades(self: Student) {
    return this.http.get<any>('http://localhost:8080/api/grade/frequency/' + self.email)
      .pipe(
        map((res) => {
          const grades = Object.values(res).map((grade: any) => grade.grade);
          return grades;
        })
      );
  }

}
