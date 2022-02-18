import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavMercanciaComponent } from './nav-mercancia.component';

describe('NavMercanciaComponent', () => {
  let component: NavMercanciaComponent;
  let fixture: ComponentFixture<NavMercanciaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NavMercanciaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NavMercanciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
