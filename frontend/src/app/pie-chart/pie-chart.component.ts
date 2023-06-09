import { Component, OnInit, Input } from '@angular/core';
import { Chart } from 'chart.js';

@Component({
  selector: 'app-pie-chart',
  templateUrl: './pie-chart.component.html',
  styleUrls: ['./pie-chart.component.css']
})
export class PieChartComponent implements OnInit {
  @Input() grades!: number[]; // Updated type declaration for grades
  chart: any;

  ngOnInit() {
    const labels = this.grades.map((_, index) => `Grade ${index + 1}`);
    const data = this.grades;
    const backgroundColors = this.grades.map(grade => this.getColorForGrade(grade));

    this.chart = new Chart('canvas', {
      type: 'pie',
      data: {
        labels: labels,
        datasets: [{
          data: data,
          backgroundColor: backgroundColors
        }]
      },
      options: {
        responsive: true
      }
    });
  }

  // Helper function to assign colors based on grade values
  getColorForGrade(grade: number): string {
    if (grade >= 9) {
      return 'green';
    } else if (grade >= 8) {
      return 'yellow';
    } else if (grade >= 7) {
      return 'orange';
    } else {
      return 'red';
    }
  }
}
