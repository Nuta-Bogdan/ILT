import {HttpClient} from "@angular/common/http";
import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import {map} from "rxjs/operators";
import {Student} from "../model/Student";
import {ViewAttendenceService} from "../services/view-attendence.service";
import {Attendance} from "../model/Attendance";
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {ReactiveFormsModule} from '@angular/forms';
import {Grade} from "../model/Grade";
import { Subscription } from 'rxjs';
import { MenuLeaderComponent } from '../menu-leader/menu-leader.component';

@Component({
  selector: 'app-view-attendence',
  templateUrl: './view-attendence.component.html',
  styleUrls: ['./view-attendence.component.css']
})
export class ViewAttendenceComponent implements OnInit {
  @Input() selectedStudent!: Student;
  refreshSubscription!: Subscription;
  attendance: any[] = [];
  dataSource: MatTableDataSource<Attendance>;
  displayedColumns: string[] = ['task', 'presence'];

  constructor(private http: HttpClient, private viewAttendenceService: ViewAttendenceService,private menuLeaderComponent: MenuLeaderComponent) {
    this.dataSource = new MatTableDataSource<Attendance>([]);
  }

  ngOnInit() {
    this.viewAttendence();
    this.refreshSubscription = this.menuLeaderComponent.refreshDataSubject.subscribe(() => {
      // Refresh attendance data here
      // You can make an API call or update the data directly if it's available in this component
    });
  }
  ngOnChanges(changes: SimpleChanges) {
    if (changes['selectedStudent'] && !changes['selectedStudent'].firstChange) {
      // Handle the change in selectedStudent here
      // Reload the attendance data or update the component accordingly
      this.viewAttendence();
    }
  }

  private viewAttendence() {
    this.viewAttendenceService.ViewAttendence(this.selectedStudent)
      .pipe(
        map((res: Attendance[]) => {
          // Filter out the tuples with non-empty data
          return res.filter((attendance: Attendance) => Object.keys(attendance).length > 1);
        })
      )
      .subscribe((filteredAttendance: Attendance[]) => {
        this.attendance = filteredAttendance;
        this.dataSource.data = this.attendance;
        console.log(filteredAttendance);
      });
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    console.log((event.target as HTMLInputElement).value);
  }
  ngOnDestroy() {
    this.refreshSubscription.unsubscribe();
  }
}
