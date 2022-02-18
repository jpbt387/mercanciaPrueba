import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MercancialistComponent } from './mercancialist.component';

describe('MercancialistComponent', () => {
  let component: MercancialistComponent;
  let fixture: ComponentFixture<MercancialistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MercancialistComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MercancialistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
