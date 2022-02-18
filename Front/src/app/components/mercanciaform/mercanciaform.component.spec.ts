import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MercanciaformComponent } from './mercanciaform.component';

describe('MercanciaformComponent', () => {
  let component: MercanciaformComponent;
  let fixture: ComponentFixture<MercanciaformComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MercanciaformComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MercanciaformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
