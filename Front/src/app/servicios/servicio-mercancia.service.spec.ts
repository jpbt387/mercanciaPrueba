import { TestBed } from '@angular/core/testing';

import { ServicioMercanciaService } from './servicio-mercancia.service';

describe('ServicioMercanciaService', () => {
  let service: ServicioMercanciaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServicioMercanciaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
