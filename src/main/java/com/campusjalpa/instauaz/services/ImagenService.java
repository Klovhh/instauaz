package com.campusjalpa.instauaz.services;

import com.campusjalpa.instauaz.models.Imagen;
import com.campusjalpa.instauaz.models.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImagenService {

    private final ImagenRepository imagenRepository;

    @Autowired
    public ImagenService(ImagenRepository imagenRepository) {
        this.imagenRepository = imagenRepository;
    }

    // Obtener todas las imágenes
    public List<Imagen> getImagen() {
        return imagenRepository.findAll();
    }

    // Eliminar una imagen por su ID
    public void deleteImagen(int imagen_id) {
        boolean exist = imagenRepository.existsById(imagen_id);
        if (!exist) {
            // La imagen no existe, no se realiza ninguna acción
        } else {
            imagenRepository.deleteById(imagen_id);
        }
    }

    // Actualizar información de una imagen por su ID
    @Transactional
    public void updateImagen(Integer imagenId, String info) {
        Imagen imagen = imagenRepository.findById(imagenId)
                .orElseThrow(() -> new IllegalStateException("La imagen no existe"));

        if (info != null) {
            imagen.setImagen_info(info);
        }
    }

    // Obtener imágenes por ID de usuario
    public List<Imagen> getImagenByUsuarioId(Integer id_usuario) {
        List<Imagen> filteredImagenes = new ArrayList<>();

        for (Imagen imagen : getImagen()) {
            if (imagen.getId_usuario() == id_usuario) {
                filteredImagenes.add(imagen);
            }
        }

        return filteredImagenes;
    }
}
