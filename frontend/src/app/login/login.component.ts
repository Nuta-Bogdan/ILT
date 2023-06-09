import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from "@angular/forms";
import { AuthService } from '../services/auth.service';
import {Student} from "../model/Student";
import { HttpClient } from '@angular/common/http';
export interface Account
{
  email:string;
  userTpe:string;
}
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent {
  userType!:string;
  constructor(private router: Router,  private http: HttpClient,private authService: AuthService) {

  }

  login(form: NgForm) {
    if (form.valid) {
      const username = form.value.username;
      const password1 = form.value.password;

      this.http.post<any>('http://localhost:8080/api/login', { email: username, password: password1 })
        .subscribe((response) => {
          console.log(response);
          this.userType = response.userType;

          // Perform login logic and determine the user type

          // Navigate to the appropriate component based on the user type
          switch (this.userType) {
            case 'MENTOR':
              this.router.navigate(['/mentor']);
              const St = new Student("ana", username, false);
              this.authService.setLoggedInUser(St);
              break;
            case 'STUDENT':
              this.router.navigate(['/student']);
              const St1 = new Student("ana", username, false);
              this.authService.setLoggedInUser(St1);
              break;
            case 'LEADER':
              this.router.navigate(['/leader']);
              const St2 = new Student("ana", username, true);
              this.authService.setLoggedInUser(St2);
              break;
            default:
              // Handle unknown user type or show an error message
              break;
          }
        });
    }
  }

}
