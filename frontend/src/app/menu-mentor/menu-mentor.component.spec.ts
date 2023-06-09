import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuMentorComponent } from './menu-mentor.component';

describe('MenuMentorComponent', () => {
  let component: MenuMentorComponent;
  let fixture: ComponentFixture<MenuMentorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MenuMentorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MenuMentorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
