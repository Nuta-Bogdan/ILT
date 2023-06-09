import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuLeaderComponent } from './menu-leader.component';

describe('MenuLeaderComponent', () => {
  let component: MenuLeaderComponent;
  let fixture: ComponentFixture<MenuLeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MenuLeaderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MenuLeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
