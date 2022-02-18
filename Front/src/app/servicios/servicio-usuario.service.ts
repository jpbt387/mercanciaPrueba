import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Constantes } from '../utilidades/constantes';

@Injectable({
  providedIn: 'root'
})
export class ServicioUsuarioService {

  constructor(private http:HttpClient) { }

  listarUsuarios():Observable<any>
  {
    let url:string = Constantes.urlUsuario+"listar";
    console.log("url : ",url);
    let tokenSession:any = sessionStorage.getItem("token")?.toString();
    return this.http.get(url,{
      params:{
        token:tokenSession
      }
    });
  }
}
