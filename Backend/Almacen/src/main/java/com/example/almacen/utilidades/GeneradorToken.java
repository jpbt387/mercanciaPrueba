package com.example.almacen.utilidades;


import java.util.Date;
import java.util.HashMap;

import com.example.almacen.model.Usuario;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class GeneradorToken 
{
	public static String generarToken(Usuario usuario)
	{
		HashMap<String, Object> data = new HashMap<>();
		data.put("usr", usuario.getIdUsuario());
		data.put("cpf", usuario.getIdCargo().getIdCargo());
		Date fechaActual = new Date();
		Long fechaActualMilisegundos = fechaActual.getTime();
		Long fechaFinalMilisegundos = fechaActualMilisegundos + Long.valueOf(String.valueOf("3600000"));
		String token = Jwts.builder().setClaims(data).setExpiration(new Date(fechaFinalMilisegundos)).signWith(SignatureAlgorithm.HS256, "secreto").compact();
		return token;
	}
	
	
}
