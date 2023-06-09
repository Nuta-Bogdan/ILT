import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Student} from "../model/Student";
import {map} from "rxjs";

@Injectable({providedIn: "root"})
export class ViewTeamService{
  constructor(private http: HttpClient) {
  }
  ViewTeamLeaders() {
    return this.http.get<{ [key: string]: Student }>('http://localhost:8080/api/student/leaders')
      .pipe(map((res) => {
        const Leaders = [];
        for (const key in res) {
          if (res.hasOwnProperty(key)) {
            Leaders.push({...res[key], id: key})
          }
        }
        return Leaders;
      }))
  }
  onSelectViewGradesAndAttendance(){
    return this.http.get('')
  }


}
