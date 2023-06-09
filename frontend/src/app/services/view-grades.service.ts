import { Injectable } from '@angular/core';
import { map } from "rxjs/operators";
import { Student } from "../model/Student";
import { HttpClient } from "@angular/common/http";
import {Grade} from "../model/Grade";


@Injectable({
  providedIn: 'root'
})
export class ViewGradesService {

  constructor(private http: HttpClient) {}
  ViewGrades(self:Student)
  {
    return this.http.get<{ [key: string]: Grade }>('http://localhost:8080/api/grade/' + self.email)
      .pipe(
        map((res) => {
          const grades = [];
          for (const key in res) {
            if (res.hasOwnProperty(key)) {
              if(key!=="id")
                grades.push({ ...res[key], id: key });
            }
          }
          return grades;
        })
      );
  }
}
