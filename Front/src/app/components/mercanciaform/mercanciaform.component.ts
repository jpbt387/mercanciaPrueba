import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ServicioMercanciaService } from 'src/app/servicios/servicio-mercancia.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-mercanciaform',
  templateUrl: './mercanciaform.component.html',
  styleUrls: ['./mercanciaform.component.scss']
})
export class MercanciaformComponent implements OnInit {

  formulario:FormGroup;
  productoEditar:any;
  editar:boolean = false;
  constructor(private servicio:ServicioMercanciaService,private route:ActivatedRoute) {
    this.formulario = new FormGroup({
      nombre:new FormControl(''),
      cantidad: new FormControl('')
    })
  }

  ngOnInit(): void {
    let id:any = this.route.snapshot.paramMap.get('id');
    if(id!=undefined)
    {
      this.servicio.traerProducto(id).subscribe(respuesta =>{
        if(respuesta.estado ==1)
        {
          console.log("respuesta : ",respuesta);
          this.formulario.patchValue({
            nombre:respuesta.producto.nombreProducto,
            cantidad:respuesta.producto.cantidad
          })
          this.productoEditar = respuesta.producto;
          this.editar = true;
        }else{
          console.log("error: ",respuesta.mensaje);
        }
      })
    }
  }

  validarFormato(entradaCantidad:FormControl)
  {

  }

  guardarProducto()
  {
    if(this.productoEditar==undefined)
    {
      let producto = {
        usuario:-1,
        editor:-1,
        nombreProducto:this.formulario.get('nombre')?.value,
        cantidad:this.formulario.get('cantidad')?.value
      };
      this.servicio.crearProducto(producto).subscribe(respuesta =>{
        console.log("respuesta : ",respuesta);
        if(respuesta.estado == 1)
        {
          Swal.fire({
            title: 'ÉXITO',
            text: 'PRODUCTO CREADO SATISFACTORIAMENTE',
            icon:'success',
            showConfirmButton:true
          }).then(resp =>
            {
              if(resp.isConfirmed)
              {
                window.location.href = "/producto";
              }
            })
        }else{
          console.log("ERROR : ",respuesta.mensaje);
        }
      });
    }else{
      let producto = {
        idProducto:this.productoEditar.idProducto,
        editor:-1,
        nombreProducto:this.formulario.get('nombre')?.value,
        cantidad:this.formulario.get('cantidad')?.value
      };
      this.servicio.editarProducto(producto).subscribe(respuesta =>{
        console.log("respuesta : ",respuesta);
        if(respuesta.estado == 1)
        {
          Swal.fire({
            title: 'ÉXITO',
            text: 'PRODUCTO MODIFICADO SATISFACTORIAMENTE',
            icon:'success',
            showConfirmButton:true
          }).then(resp =>
            {
              if(resp.isConfirmed)
              {
                window.location.href = "/producto";
              }
            })
        }else{
          console.log("ERROR : ",respuesta.mensaje);
        }
      });
    }
  }



}
