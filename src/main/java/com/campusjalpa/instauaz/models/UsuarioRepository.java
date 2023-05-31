package com.campusjalpa.instauaz.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value = "SELECT * FROM usuario WHERE nom_usuario = :nombre AND con_usuario = :password", nativeQuery = true)
    List<Usuario> findUsuario(@Param("nombre") String nombre, @Param("password") String password);



}
