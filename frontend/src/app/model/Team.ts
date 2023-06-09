import {Student} from "./Student";

export class Team{
  constructor(Activity: string, Students: Student[]) {
    this.Activity = Activity;
    this.Students = Students;
  }
  Activity: string;
  Students: Student[];


}
