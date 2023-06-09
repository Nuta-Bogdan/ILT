import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PdfToPngComponent } from './pdf-to-png.component';

describe('PdfToPngComponent', () => {
  let component: PdfToPngComponent;
  let fixture: ComponentFixture<PdfToPngComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PdfToPngComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PdfToPngComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
