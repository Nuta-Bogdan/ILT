import {HttpClient} from "@angular/common/http";
import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import {map} from "rxjs/operators";
import {Student} from "../model/Student";
import {Grade} from "../model/Grade";
import {ViewGradesService} from "../services/view-grades.service";
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {ReactiveFormsModule} from '@angular/forms';
import { Subscription } from 'rxjs';
import { MenuLeaderComponent } from '../menu-leader/menu-leader.component';
@Component({
  selector: 'app-view-grades',
  templateUrl: './view-grades.component.html',
  styleUrls: ['./view-grades.component.css']
})
export class ViewGradesComponent implements OnInit {
  @Input() selectedStudent!: Student;
  refreshSubscription!: Subscription;
  grades: Grade[] = [];
  dataSource: MatTableDataSource<Grade>;


  displayedColumns: string[] = ['task', 'mentor', 'grade', 'comment'];

  constructor(private http: HttpClient, private viewGradesService: ViewGradesService,private menuLeaderComponent: MenuLeaderComponent) {
    this.dataSource = new MatTableDataSource<Grade>([]);

  }

  ngOnInit() {
    this.viewGrades();
    this.refreshSubscription = this.menuLeaderComponent.refreshDataSubject.subscribe(() => {
      // Refresh attendance data here
      // You can make an API call or update the data directly if it's available in this component
    });


  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['selectedStudent'] && !changes['selectedStudent'].firstChange) {
      // Handle the change in selectedStudent here
      // Reload the grades data or update the component accordingly
      this.viewGrades();
    }
  }

  private viewGrades() {
    this.viewGradesService.ViewGrades(this.selectedStudent)
      .pipe(
        map((res: Grade[]) => {
          return res.filter((grade: Grade) => Object.keys(grade).length > 1);
        })
      )
      .subscribe((filteredGrades: Grade[]) => {
        this.grades = filteredGrades;
        this.dataSource.data = this.grades;
        console.log(filteredGrades);
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
