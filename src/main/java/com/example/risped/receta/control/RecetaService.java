package com.example.risped.receta.control;

import com.example.risped.receta.model.Receta;
import com.example.risped.receta.model.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class RecetaService {

    private final RecetaRepository recetaRepository;

    @Autowired
    public RecetaService(RecetaRepository recetaRepository) {
        this.recetaRepository = recetaRepository;
    }

    // Obtener todas las recetas
    @Transactional(readOnly = true)
    public List<Receta> findAll() {
        return recetaRepository.findAll();
    }

    // Guardar una nueva receta
    @Transactional
    public Receta save(Receta receta) {
        return recetaRepository.save(receta);
    }

    // Actualizar una receta existente
    @Transactional
    public Receta update(Long id, Receta receta) {
        Optional<Receta> existingReceta = recetaRepository.findById(id);
        if (existingReceta.isPresent()) {
            Receta updatedReceta = existingReceta.get();
            updatedReceta.setMedicamentos(receta.getMedicamentos());
            updatedReceta.setSugerencias(receta.getSugerencias());
            updatedReceta.setFecha(receta.getFecha());
            updatedReceta.setNombreDoctor(receta.getNombreDoctor());
            updatedReceta.setPaciente(receta.getPaciente());
            return recetaRepository.save(updatedReceta);
        }
        return null; // Manejar este caso según sea necesario
    }

    // Eliminar una receta por ID
    @Transactional
    public void deleteById(Long id) {
        recetaRepository.deleteById(id);
    }

    // Buscar una receta por ID
    @Transactional(readOnly = true)
    public Optional<Receta> findById(Long id) {
        return recetaRepository.findById(id);
    }
}
