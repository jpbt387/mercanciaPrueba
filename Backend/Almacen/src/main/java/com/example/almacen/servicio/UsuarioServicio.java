package com.example.almacen.servicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.almacen.model.Usuario;
import com.example.almacen.repository.UsuarioRepositorio;

@Service
public class UsuarioServicio{
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	
	public void crearUsuario(Usuario usuario)
	{
		usuario.setIdCargo(null);
		usuarioRepositorio.save(usuario);
	}

	
	public void actualizarUsuario(Usuario usuario)
	{
		usuarioRepositorio.save(usuario);
	}

	
	public Usuario traerUsuario(Integer idUsuario)
	{
		Optional<Usuario> objetoUsuario = usuarioRepositorio.findById(idUsuario);
		List<Usuario> lista = usuarioRepositorio.findAll();
		System.out.println("llamando servicio : "+lista.size());
		if(objetoUsuario.isPresent())
		{
			return objetoUsuario.get();
		}else
		{
			return null;
		}
	}
	
	public List<HashMap<String, Object>> listarUsuarios()
	{
		List<Usuario> usuariosSistema = usuarioRepositorio.findAll();
		List<HashMap<String,Object>> usuariosSalidad = new ArrayList<HashMap<String,Object>>();
		for(Usuario usuario: usuariosSistema)
		{
			HashMap<String, Object> usuarioSalida = new HashMap<String, Object>();
			usuarioSalida.put("nombre", usuario.getNombreUsuario());
			usuarioSalida.put("cargo", usuario.getIdCargo().getDescripcionCargo());
			usuarioSalida.put("idcargo", usuario.getIdCargo().getIdCargo());
			usuarioSalida.put("idUsuario", usuario.getIdUsuario());
			usuariosSalidad.add(usuarioSalida);
		}
		return usuariosSalidad;
		
	}

}
