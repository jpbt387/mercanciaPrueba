import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Constantes } from '../utilidades/constantes';
import { HttpClient } from '@angular/common/http';
import jwt_decode from "jwt-decode" ;

@Injectable({
  providedIn: 'root'
})
export class ServicioMercanciaService {

  constructor(private http:HttpClient) { }

  traerMercancia():Observable<any>
  {
    let url:string = Constantes.urlMercancia+"listar";
    console.log("url : ",url);
    let tokenSession:any = sessionStorage.getItem("token")?.toString();
    return this.http.get(url,{
      params:{
        token:tokenSession
      }
    });
  }

  traerProducto(id:number):Observable<any>
  {
    let url:string = Constantes.urlMercancia+"traer/"+id;
    console.log("url : ",url);
    let tokenSession:any = sessionStorage.getItem("token")?.toString();
    return this.http.get(url,{
      params:{
        token:tokenSession
      }
    });
  }

  traerToken(id:number):Observable<any>
  {
    let url:string = Constantes.urlUsuario+"traer/"+id;
    console.log("url : ",url);
    return this.http.get(url);
  }

  crearProducto(producto:any):Observable<any>
  {
    let url:string = Constantes.urlMercancia+"crear";
    var token:any = sessionStorage.getItem("token")?.toString();
    var dataToken:any = jwt_decode(token);
    producto.usuario = {
      idUsuario:dataToken.usr
    }

    producto.editor =
    {
      idUsuario:dataToken.usr
    }
    console.log("producto enviado : ",producto);
    return this.http.post(url,producto,{
      params:{
        token:token
      }
    })
  }

  editarProducto(producto:any):Observable<any>
  {
    let url:string = Constantes.urlMercancia+"modificar";
    var token:any = sessionStorage.getItem("token")?.toString();
    var dataToken:any = jwt_decode(token);
    producto.usuario = {
      idUsuario:dataToken.usr
    }

    producto.editor =
    {
      idUsuario:dataToken.usr
    }
    return this.http.put(url,producto,{
      params:{
        token:token
      }
    })
  }

  eliminarProducto(producto:any):Observable<any>
  {
    let url:string = Constantes.urlMercancia+"eliminar/"+producto;
    var token:any = sessionStorage.getItem("token")?.toString();
    return this.http.delete(url,{
      params:{
        token:token
      }
    })
  }
}
