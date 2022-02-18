import { Component, OnInit } from '@angular/core';
import { ServicioMercanciaService } from 'src/app/servicios/servicio-mercancia.service';
import jwt_decode from "jwt-decode" ;
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-mercancia-table',
  templateUrl: './mercancia-table.component.html',
  styleUrls: ['./mercancia-table.component.scss']
})
export class MercanciaTableComponent implements OnInit {

  listaProductos:any[] = [];
  criterio:any;
  valorFiltro:string="";
  constructor(private servicio:ServicioMercanciaService,private router:Router) { }

  ngOnInit(): void {
    this.traerMercancia();
  }

  traerMercancia()
  {
    this.servicio.traerMercancia().subscribe(respuesta =>
      {
        console.log("respuesta rescibida : ",respuesta);
        if(respuesta.estado == 1)
        {
          this.listaProductos = respuesta.lista;
        }else{
          console.log("error : ",respuesta.mensaje);
        }
      })
  }

  habilitado(producto:any):boolean
  {
    var token:any = sessionStorage.getItem("token")?.toString();
    var dataToken:any = jwt_decode(token);
    if(producto.idCreador.toString() == dataToken.usr)
    {
      return true;
    }else{
      return false;
    }
  }

  editarProducto(producto:any)
  {
    this.router.navigate(['editar',producto.idProducto]);
  }

  eliminarProducto(producto:number)
  {
    this.servicio.eliminarProducto(producto).subscribe(respuesta =>{
      console.log("respuesta : ",respuesta);
        if(respuesta.estado == 1)
        {
          Swal.fire({
            title: 'ÉXITO',
            text: 'PRODUCTO ELIMINADO SATISFACTORIAMENTE',
            icon:'success',
            showConfirmButton:true
          }).then(resp =>
            {
              if(resp.isConfirmed)
              {
                this.traerMercancia();
              }
            })
        }else{
          console.log("ERROR : ",respuesta.mensaje);
        }
    })
  }

  buscar(criterio:any)
  {
    this.criterio = criterio;
  }

  filtrar()
  {
    if(this.criterio == undefined || this.valorFiltro == "")
    {
      Swal.fire({
        title: 'DATOS INCOMPLETOS',
        text: 'DEBE ESPECIFICAR EL TIPO DE FILTRO Y EL VALOR DEL FILTRO',
        icon:'warning'
      })
    }else{
      if(this.criterio == 1)
    {
      this.listaProductos= this.listaProductos.filter(producto =>JSON.stringify(producto.creador).includes(this.valorFiltro.toLowerCase()));
    }else if(this.criterio == 2)
    {
      this.listaProductos =this.listaProductos.filter(producto => JSON.stringify(producto.nombre).includes(this.valorFiltro.toLowerCase()));
    }
    }

  }

}
