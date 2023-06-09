import { Component } from '@angular/core';
import { Input, ViewChild, ElementRef } from "@angular/core";
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import {HttpClient} from "@angular/common/http";
import { Observable } from 'rxjs';
import {NgForm} from "@angular/forms";
@Component({
  selector: 'app-apply',
  templateUrl: './apply.component.html',
  styleUrls: ['./apply.component.css']
})
export class ApplyComponent {

  constructor( private http: HttpClient)
  {


  }
  selected!:boolean;

  activities!:string;
  sent:boolean=false;
showActivity()
{
  this.http.get<any>('http://localhost:8080/api/activity/getNames')
    .subscribe(data => {
    this.activities = data;
    console.log(this.activities);
  });
  this.selected=true;

}


SubmitForm(form: NgForm)
{
  const name = form.value.name;
  const email = form.value.email;
  const password = form.value.password;
  const activity = form.value.activity;
  console.log(`Name: ${name}`);
  console.log(`Email: ${email}`);
  console.log(`Password: ${password}`);
  console.log(`Activity: ${activity}`);
  this.http.post('http://localhost:8080/api/register',{activityName:activity,emailLeader:email,nameLeader:name,passwordLeader:password})
    .subscribe((res)=>{
    });

}
}
