package com.example.almacen.servicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.almacen.model.Mercancia;
import com.example.almacen.repository.MercanciaRepositorio;

@Service
public class MercanciaServicio {
	
	@Autowired
	private MercanciaRepositorio mercanciaRepositorio;

	public void crearMercancia(Mercancia mercancia)
	{
		mercancia.setIdProducto(null);
		mercanciaRepositorio.save(mercancia);
	}

	
	public void actualizarMercancia(Mercancia mercancia)
	{
		mercanciaRepositorio.save(mercancia);
	}

	
	public void eliminarMercancia(Integer idMercancia)
	{
		mercanciaRepositorio.deleteById(idMercancia);
	}

	
	public Mercancia traerProducto(Integer idMercancia)
	{
		Optional<Mercancia> objetoMercancia = mercanciaRepositorio.findById(idMercancia);
		if(objetoMercancia.isPresent())
		{
			return objetoMercancia.get();
		}else {
			return null;
		}
	}

	
	public List<HashMap<String, Object>> traerProductos()
	{
		List<Mercancia> listaProductosSistema = mercanciaRepositorio.findAll();
		List<HashMap<String, Object>> listaProductosSalida = new ArrayList<>();
		for(Mercancia producto:listaProductosSistema)
		{
			HashMap<String, Object> productoSalida = new HashMap<String, Object>();
			productoSalida.put("idProducto",producto.getIdProducto());
			productoSalida.put("nombre",producto.getNombreProducto());
			productoSalida.put("cantidad",producto.getCantidad());
			productoSalida.put("fecha",producto.getFechaRegistro());
			productoSalida.put("idCreador", producto.getUsuario().getIdUsuario());
			productoSalida.put("creador",producto.getUsuario().getNombreUsuario());
			productoSalida.put("fechaEditado",producto.getFechaModificacion());
			productoSalida.put("idEditor", producto.getEditor().getIdUsuario());
			productoSalida.put("editor",producto.getEditor().getNombreUsuario());
			listaProductosSalida.add(productoSalida);
		}
		return listaProductosSalida;
	}
	
}
