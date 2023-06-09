import { Injectable } from '@angular/core';
import {Student} from "../model/Student";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loggedInUser!: Student;

  constructor() { }

  setLoggedInUser(user: Student): void {
    this.loggedInUser = user;
  }

  getLoggedInUser(): Student {
    return this.loggedInUser;
  }
}
