import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import {Student} from "../model/Student";


@Component({
  selector: 'app-menu-student',
  templateUrl: './menu-student.component.html',
  styleUrls: ['./menu-student.component.css']
})
export class MenuStudentComponent {

  selectedComponent: string = '';
  selectedUser:Student;
  showComponent(componentName: string) {
    this.selectedComponent = componentName;
  }
constructor( private authService: AuthService)
{
  this.selectedUser=this.authService.getLoggedInUser();
}
  showStudentData(student: Student) {
    this.selectedUser = student;
  }

}
