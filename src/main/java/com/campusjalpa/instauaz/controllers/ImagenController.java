package com.campusjalpa.instauaz.controllers;


import com.campusjalpa.instauaz.models.Imagen;
import com.campusjalpa.instauaz.services.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/imagenes")
public class ImagenController {
    private JWTUtil jwtUtil; // Utilidad para manejar JWT

    private final ImagenService imagenService; // Servicio de imágenes

    @Autowired
    public ImagenController(ImagenService imagenService) {
        this.imagenService = imagenService;
    }

    // Obtener todas las imágenes
    @GetMapping
    public List<Imagen> getImagen(@RequestHeader(value = "Authorization") String token) {
        String usuarioId = jwtUtil.getKey(token); // Obtener el ID del usuario a partir del token JWT
        if (usuarioId == null) {
            return new ArrayList<>(); // Devolver una lista vacía si el ID del usuario es nulo
        }

        return imagenService.getImagen(); // Obtener todas las imágenes a través del servicio
    }

    // Buscar imágenes por ID de usuario
    @GetMapping(path = "find/{id_usuario}")
    public List<Imagen> findImagen(@PathVariable int id_usuario, @RequestHeader(value = "Authorization") String token) {
        return imagenService.getImagenByUsuarioId(id_usuario); // Obtener imágenes por ID de usuario a través del servicio
    }

    // Eliminar una imagen por su ID
    @DeleteMapping(path = "delete/{imagen_id}")
    public void deleteImagen(@PathVariable int imagen_id) {
        imagenService.deleteImagen(imagen_id); // Eliminar la imagen a través del servicio
    }

    // Actualizar información de una imagen por su ID
    @PostMapping("/update/{imagen_id}")
    public void updateImagen(@PathVariable("imagen_id") Integer imagenId, @RequestParam String info) {
        imagenService.updateImagen(imagenId, info); // Actualizar la información de la imagen a través del servicio
    }
}

