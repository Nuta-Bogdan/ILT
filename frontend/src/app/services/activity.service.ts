import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class ActivityService{

  constructor(private http: HttpClient) {
  }
  getProjects(){
    return this.http.get<any>('http://localhost:8080/api/activity/getNames')
  }

}
