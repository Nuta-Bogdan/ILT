import {Component, ViewChild, ElementRef} from '@angular/core';
import {DataSource} from '@angular/cdk/collections';
import {Observable, ReplaySubject} from 'rxjs';

export interface PeriodicElement {
  leader: string;
  email: string;
  number: number;
  cvs: string[];
}

const ELEMENT_DATA: PeriodicElement[] = [
  {leader: 'John Doe', email: 'johndoe@example.com', number: 1, cvs: ['CV1', 'CV2']},
  {leader: 'Jane Smith', email: 'janesmith@example.com', number: 2, cvs: ['CV3']},
];

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  @ViewChild('pdfViewer') pdfViewer: ElementRef | undefined;

  displayedColumns: string[] = ['number', 'leader', 'email', 'cvs'];
  dataToDisplay = [...ELEMENT_DATA];
  currentCvIndex = 0;
  selectedCv: string = ''; // initialize selectedCv

  dataSource = new ExampleDataSource(this.dataToDisplay);

  addData() {
    const randomElementIndex = Math.floor(Math.random() * ELEMENT_DATA.length);
    this.dataToDisplay = [...this.dataToDisplay, ELEMENT_DATA[randomElementIndex]];
    this.dataSource.setData(this.dataToDisplay);
  }

  removeData() {
    this.dataToDisplay = this.dataToDisplay.slice(0, -1);
    this.dataSource.setData(this.dataToDisplay);
  }

  viewCv() {
    const lastDisplayedRow = this.dataToDisplay[this.dataToDisplay.length - 1];
    const currentCv = lastDisplayedRow.cvs[this.currentCvIndex];
    const pdfUrl = `/assets/cvs/${currentCv}.pdf`;
    // @ts-ignore
    this.pdfViewer.nativeElement.src = pdfUrl;
    this.selectedCv = this.dataToDisplay[this.dataToDisplay.length - 1].cvs[this.currentCvIndex]; // update selectedCv
  }

  nextCv() {
    const lastDisplayedRow = this.dataToDisplay[this.dataToDisplay.length - 1];
    const numCvs = lastDisplayedRow.cvs.length;
    if (this.currentCvIndex === numCvs - 1) {
      this.currentCvIndex = 0;
    } else {
      this.currentCvIndex++;
    }

    const currentIndex = this.dataToDisplay.findIndex(element => element.cvs.includes(this.selectedCv)); // get current index
    const nextIndex = (currentIndex + 1) % this.dataToDisplay.length; // calculate next index (loop back to 0 if end of array is reached)
    this.selectedCv = this.dataToDisplay[nextIndex].cvs[this.currentCvIndex]; // update sele
    this.viewCv();
  }
}

class ExampleDataSource extends DataSource<PeriodicElement> {
  private _dataStream = new ReplaySubject<PeriodicElement[]>();

  constructor(initialData: PeriodicElement[]) {
    super();
    this.setData(initialData);
  }

  connect(): Observable<PeriodicElement[]> {
    return this._dataStream;
  }

  disconnect() {
  }

  setData(data: PeriodicElement[]) {
    this._dataStream.next(data);
  }
}
