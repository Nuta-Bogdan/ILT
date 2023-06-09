import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Student } from "../model/Student";
import { Attendance } from "../model/Attendance";
import { map } from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class ViewAttendenceService {

  constructor(private http: HttpClient) {}

  ViewAttendence(self: Student) {
    return this.http.get<{ [key: string]: Attendance }>('http://localhost:8080/api/attendance/' + self.email)
      .pipe(
        map((res) => {
          const attendances = [];
          for (const key in res) {
            if (res.hasOwnProperty(key)) {
              if(key!=="id")
              attendances.push({ ...res[key], id: key });
            }
          }
          return attendances;
        })
      );
  }
}
