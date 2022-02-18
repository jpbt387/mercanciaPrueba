package com.example.almacen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.almacen.model.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{

}
