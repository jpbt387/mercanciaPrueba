package com.example.almacen.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.almacen.model.Usuario;
import com.example.almacen.servicio.UsuarioServicio;
import com.example.almacen.utilidades.GeneradorToken;

@RestController
@RequestMapping("usuario")
@CrossOrigin("*")
public class UsuarioController
{
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@GetMapping("/traer/{id}")
	public ResponseEntity<?> traerUsuario(@PathVariable("id")Integer idUsuario)
	{
		HashMap<String, Object> respuesta = new HashMap<String, Object>();
		try {

				Usuario usuario = usuarioServicio.traerUsuario(idUsuario);
				
				if(usuario!= null)
				{
					String tokenSalida = GeneradorToken.generarToken(usuario);
					respuesta.put("estado",1);
					respuesta.put("usuario", usuario.getNombreUsuario());
					respuesta.put("token",tokenSalida);
					return ResponseEntity.ok().body(respuesta);
				}else
				{
					respuesta.put("estado",HttpStatus.NOT_FOUND);
					respuesta.put("mensaje","no encontrado");
					return ResponseEntity.badRequest().body(respuesta);
				}
				
			
		} catch (Exception e) 
		{
			respuesta.put("estado", HttpStatus.INTERNAL_SERVER_ERROR);
			respuesta.put("mensaje", e.getMessage());
			return ResponseEntity.internalServerError().body(respuesta);
		}	
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listarUsuario(@RequestParam String token)
	{
		HashMap<String, Object> respuesta = new HashMap<String, Object>();
		try {
				if(token!= null)
				{
					List<HashMap<String, Object>> usuarios = usuarioServicio.listarUsuarios();
					respuesta.put("estado",1);
					respuesta.put("usuarios",usuarios);
					return ResponseEntity.ok().body(respuesta);
				}else
				{
					respuesta.put("estado",HttpStatus.UNAUTHORIZED);
					respuesta.put("mensaje","acceso restringido");
					return ResponseEntity.badRequest().body(respuesta);
				}
				
			
		} catch (Exception e) 
		{
			respuesta.put("estado", HttpStatus.INTERNAL_SERVER_ERROR);
			respuesta.put("mensaje", e.getMessage());
			return ResponseEntity.internalServerError().body(respuesta);
		}	
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> crearUsuario(@RequestParam String token,@RequestBody Usuario usuario)
	{
		HashMap<String, Object> respuesta = new HashMap<String, Object>();
		try {
				if(token!= null)
				{
					usuarioServicio.crearUsuario(usuario);
					respuesta.put("estado",1);
					respuesta.put("mensaje","creado");
					return ResponseEntity.ok().body(respuesta);
				}else
				{
					respuesta.put("estado",HttpStatus.UNAUTHORIZED);
					respuesta.put("mensaje","acceso restringido");
					return ResponseEntity.badRequest().body(respuesta);
				}
		} catch (Exception e) 
		{
			respuesta.put("estado", HttpStatus.INTERNAL_SERVER_ERROR);
			respuesta.put("mensaje", e.getMessage());
			return ResponseEntity.internalServerError().body(respuesta);
		}	
	}
}
