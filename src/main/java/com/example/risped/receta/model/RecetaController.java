package com.example.risped.receta.model;

import com.example.risped.receta.control.RecetaService;
import com.example.risped.receta.model.Receta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {

    private final RecetaService recetaService;

    @Autowired
    public RecetaController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    // Obtener todas las recetas
    @GetMapping
    public ResponseEntity<List<Receta>> getAllRecetas() {
        List<Receta> recetas = recetaService.findAll();
        return new ResponseEntity<>(recetas, HttpStatus.OK);
    }

    // Obtener una receta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Receta> getRecetaById(@PathVariable Long id) {
        Optional<Receta> receta = recetaService.findById(id);
        return receta.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Crear una nueva receta
    @PostMapping
    public ResponseEntity<Receta> createReceta(@RequestBody Receta receta) {
        Receta newReceta = recetaService.save(receta);
        return new ResponseEntity<>(newReceta, HttpStatus.CREATED);
    }

    // Actualizar una receta existente
    @PutMapping("/{id}")
    public ResponseEntity<Receta> updateReceta(@PathVariable Long id, @RequestBody Receta receta) {
        Receta updatedReceta = recetaService.update(id, receta);
        if (updatedReceta == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedReceta, HttpStatus.OK);
    }

    // Eliminar una receta por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceta(@PathVariable Long id) {
        recetaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
