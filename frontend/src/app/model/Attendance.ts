export class Attendance
{
  email:string;
  task:string;
  present:boolean;


  constructor(email:string,task:string,present:boolean)
  {
    this.email=email;
    this.task=task;
    this.present=present;

  }

}
