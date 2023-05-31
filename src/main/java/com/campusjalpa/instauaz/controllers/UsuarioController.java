package com.campusjalpa.instauaz.controllers;

import com.campusjalpa.instauaz.models.Usuario;
import com.campusjalpa.instauaz.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/usuarios")
public class UsuarioController {

    private JWTUtil jwtUtil; // Utilidad para manejar JWT

    private final UsuarioService usuarioService; // Servicio de usuarios

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Obtener todos los usuarios
    @GetMapping
    public List<Usuario> getUsuario() {
        return usuarioService.getUsuario(); // Obtener todos los usuarios a través del servicio
    }

    // Buscar usuario por su ID
    @GetMapping(path = "find/{id_usuario}")
    public List<Usuario> findUsuario(@PathVariable int id_usuario, @RequestHeader(value = "Authorization") String token) {
        return usuarioService.getUsuarioById(id_usuario); // Obtener usuario por ID a través del servicio
    }

    // Eliminar un usuario por su ID
    @DeleteMapping(path = "delete/{usuario_id}")
    public void deleteUsuario(@PathVariable int usuario_id) {
        usuarioService.deleteUsuario(usuario_id); // Eliminar el usuario a través del servicio
    }
}

