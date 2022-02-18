import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MercanciaTableComponent } from './mercancia-table.component';

describe('MercanciaTableComponent', () => {
  let component: MercanciaTableComponent;
  let fixture: ComponentFixture<MercanciaTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MercanciaTableComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MercanciaTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
