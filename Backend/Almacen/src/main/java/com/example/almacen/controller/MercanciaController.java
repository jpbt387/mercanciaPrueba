package com.example.almacen.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.almacen.model.Mercancia;
import com.example.almacen.servicio.MercanciaServicio;

@RestController
@RequestMapping("/mercancia")
@CrossOrigin("*")
public class MercanciaController
{
	@Autowired
	private MercanciaServicio mercanciaServicio;
	
	@GetMapping("/traer/{id}")
	public ResponseEntity<?> traerMercancia(@RequestParam String token, @PathVariable("id")Integer idMercancia)
	{
		HashMap<String, Object> respuesta = new HashMap<String, Object>();
		try {
			if(token!="")
			{
				Mercancia producto = mercanciaServicio.traerProducto(idMercancia);
				if(producto!=null)
				{
					respuesta.put("estado",1);
					respuesta.put("producto",producto);
					return ResponseEntity.ok().body(respuesta);
				}else
				{
					respuesta.put("estado",HttpStatus.NOT_FOUND);
					respuesta.put("mensaje","no encontrado");
					return ResponseEntity.ok().body(respuesta);
				}
				
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
	
	@GetMapping("/listar")
	public ResponseEntity<?> listarMercancia(@RequestParam String token)
	{
		HashMap<String, Object> respuesta = new HashMap<String, Object>();
		try {
			if(token!="")
			{
				List<HashMap<String,Object>> productosLista = mercanciaServicio.traerProductos();
				respuesta.put("estado",1);
				respuesta.put("lista",productosLista);
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
	public ResponseEntity<?> crearProducto(@RequestParam String token, @RequestBody Mercancia producto)
	{
		HashMap<String, Object> respuesta = new HashMap<String, Object>();
		try {
			if(token!="")
			{
				String strFechaActual=java.time.LocalDate.now().toString();  
			    Date fechaActual=new SimpleDateFormat("yyyy-MM-dd").parse(strFechaActual);  
				producto.setFechaRegistro(fechaActual);
				producto.setFechaModificacion(fechaActual);
				mercanciaServicio.crearMercancia(producto);
				respuesta.put("estado",1);
				respuesta.put("mensaje","producto creado");
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
	
	@PutMapping("/modificar")
	public ResponseEntity<?> editarProducto(@RequestParam String token, @RequestBody Mercancia producto)
	{
		HashMap<String, Object> respuesta = new HashMap<String, Object>();
		try {
			if(token!="")
			{
				Mercancia productoSistema = mercanciaServicio.traerProducto(producto.getIdProducto()); 
				if(productoSistema!= null)
				{
					
					productoSistema.setCantidad(producto.getCantidad());
					productoSistema.setNombreProducto(producto.getNombreProducto());
					String strFechaActual=java.time.LocalDate.now().toString();  
				    Date fechaActual=new SimpleDateFormat("yyyy-MM-dd").parse(strFechaActual);  
					productoSistema.setFechaModificacion(fechaActual);
					mercanciaServicio.actualizarMercancia(productoSistema);
					respuesta.put("estado",1);
					respuesta.put("mensaje","producto actualizado");
					ResponseEntity.ok().body(respuesta);
					return ResponseEntity.ok().body(respuesta);
				}else
				{
					respuesta.put("estado",HttpStatus.NOT_FOUND);
					respuesta.put("mensaje","producto no encontrado");
					return ResponseEntity.badRequest().body(respuesta);
				}
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
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarProducto(@RequestParam String token, @PathVariable("id")Integer idMercancia)
	{
		HashMap<String, Object> respuesta = new HashMap<String, Object>();
		try {
			if(token!="")
			{
				mercanciaServicio.eliminarMercancia(idMercancia);
				respuesta.put("estado",1);
				respuesta.put("mensaje","producto eliminado");
				ResponseEntity.ok().body(respuesta);
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
			respuesta.put("error", e.getMessage());
			return ResponseEntity.internalServerError().body(respuesta);
		}	
	}
	
	

}
