import { Component, OnInit } from '@angular/core';
import { ServicioMercanciaService } from 'src/app/servicios/servicio-mercancia.service';
import { ServicioUsuarioService } from 'src/app/servicios/servicio-usuario.service';

@Component({
  selector: 'app-nav-mercancia',
  templateUrl: './nav-mercancia.component.html',
  styleUrls: ['./nav-mercancia.component.scss']
})
export class NavMercanciaComponent implements OnInit {

  listaUsuarios:any[] = [];
  usuario:any;
  constructor(private servicioUsuarios:ServicioUsuarioService,private servicio:ServicioMercanciaService) { }

  ngOnInit(): void {
    this.traerToken();
    this.listarUsuarios();
  }

  traerToken(idusario?:any)
  {
    let idUsuario:any;
    if(idusario!=undefined)
    {
      idUsuario = idusario;
    }else{
      idUsuario= Math.floor(Math.random() * (3 - 1 + 1)) + 1;
    }

    this.servicio.traerToken(idUsuario).subscribe(respuesta =>
      {
        console.log("respuesta rescibida : ",respuesta);
        if(respuesta.estado == 1)
        {
          this.usuario = respuesta.usuario;
          sessionStorage.setItem("token",respuesta.token);
        }else{
          console.log("error : ",respuesta.mensaje);
        }
      })
  }

  listarUsuarios()
  {
    this.servicioUsuarios.listarUsuarios().subscribe(respuesta =>
      {
        console.log("respuesta rescibida : ",respuesta);
        if(respuesta.estado == 1)
        {
          this.listaUsuarios = respuesta.usuarios;
        }else{
          console.log("error : ",respuesta.mensaje);
        }
      })
  }

}
