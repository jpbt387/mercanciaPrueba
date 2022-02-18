import { TestBed } from '@angular/core/testing';

import { ServicioCargoService } from './servicio-cargo.service';

describe('ServicioCargoService', () => {
  let service: ServicioCargoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServicioCargoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
