package com.campusjalpa.instauaz.services;

import com.campusjalpa.instauaz.models.Usuario;
import com.campusjalpa.instauaz.models.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Obtener todos los usuarios
    public List<Usuario> getUsuario() {
        return usuarioRepository.findAll();
    }

    // Eliminar un usuario por su ID
    public void deleteUsuario(int id_usuario) {
        boolean exist = usuarioRepository.existsById(id_usuario);
        if (!exist) {
            // El usuario no existe, no se realiza ninguna acción
        } else {
            usuarioRepository.deleteById(id_usuario);
        }
    }

    // Obtener usuarios por su ID de usuario
    public List<Usuario> getUsuarioById(Integer id_usuario) {
        List<Usuario> filteredUsuarios = new ArrayList<>();

        for (Usuario usuario : getUsuario()) {
            if (usuario.getId_usuario() == id_usuario) {
                filteredUsuarios.add(usuario);
            }
        }

        return filteredUsuarios;
    }

    // Verificar usuario por nombre y contraseña
    public Usuario verificarUsuario(Usuario usuario) {
        String nombre = usuario.getNom_usuario();
        String password = usuario.getCon_usuario();
        List<Usuario> lista = usuarioRepository.findUsuario(nombre, password);
        if (lista.isEmpty()) {
            // No se encontró ningún usuario que coincida con el nombre y contraseña proporcionados
            return null;
        } else {
            // Se encontró un usuario que coincide con el nombre y contraseña proporcionados, se devuelve el primero de la lista
            return lista.get(0);
        }
    }
}
